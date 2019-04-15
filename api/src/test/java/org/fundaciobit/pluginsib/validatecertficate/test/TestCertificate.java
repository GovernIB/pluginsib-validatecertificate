package org.fundaciobit.pluginsib.validatecertficate.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.certificate.ICertificatePlugin;
import org.fundaciobit.plugins.certificate.InformacioCertificat;
import org.fundaciobit.plugins.certificate.ResultatValidacio;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

/**
 * 
 * @author anadal
 *
 */
public class TestCertificate  {

  public Logger log = Logger.getLogger(TestCertificate.class);

  protected Map<String, InfoResultTest> executeTests(ICertificatePlugin plugin,
      Properties testProp, File resultsDir, File expectedDir, boolean printResult,
      boolean stopWhenError) throws JAXBException, Exception, FileNotFoundException,
      PropertyException {
    String testsStr = testProp.getProperty("tests");

    String[] tests = testsStr.split(",");
    
    System.out.println("");
    

    JAXBContext jaxbContext = JAXBContext.newInstance(ResultatValidacio.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

    Map<String, InfoResultTest> resultats = new HashMap<String, InfoResultTest>();

    for (int i = 0; i < tests.length; i++) {

      String name = tests[i];

      System.out.println(" =============== TEST [" + name + "] ================");

      try {

        String certInfo = testProp.getProperty(name);

        X509Certificate certificat;
        File certFile;

        if (certInfo.indexOf('|') == -1) {

          // Certificat tipus .cer

          certFile = new File(certInfo);

          certificat = CertificateUtils.decodeCertificate(new FileInputStream(certFile));

        } else {
          // Certificat tipus .p12

          String[] fields = certInfo.split("\\|");

          String filePath = fields[0];
          String passwordks = fields[1];

          certFile = new File(filePath);

          List<Certificate> cc = CertificateUtils.readCertificatesOfKeystore(
              new FileInputStream(certFile), passwordks);
          if (cc == null || cc.size() == 0) {
            throw new Exception("Certificat amb id " + name + " esta buit.");
          }
          certificat = (X509Certificate) cc.get(0);

        }

        ResultatValidacio rv = plugin.getInfoCertificate(certificat);

        String filename = tests[i] + "_" + certFile.getName() + ".xml";
        File file = new File(resultsDir, filename);

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(rv, file);

        File expectedFile = new File(expectedDir, filename);

        String[] errorsComparacio = null;
        String warning = null;

        ResultatValidacio expected = null;
        if (expectedFile.exists()) {
          // Podem Comparar
          expected = (ResultatValidacio) jaxbUnMarshaller
              .unmarshal(new FileInputStream(expectedFile));

          errorsComparacio = compare(expected, rv);

          if (errorsComparacio != null) {
            if (printResult) {
              System.err.println("El resultat del test " + name
                  + " no és l'esperat. Diferències(" + errorsComparacio.length + "): ");
              
              
              for(String e: errorsComparacio) {
                System.err.println("  + " + e);
              }
              
              
              System.err.flush();
            }
          }

        } else {
          warning = " No existeix fitxer per comparar dins la carpeta expected (" + expectedFile.getAbsolutePath() + ")";
        }

        resultats.put(name, new InfoResultTest(expected, rv, errorsComparacio, warning));

        if (printResult) {
          System.out.println(rv.toString());
        }

        if (errorsComparacio != null && stopWhenError) {
          return resultats;
        }

      } catch (Throwable th) {

        th.printStackTrace(System.err);
        resultats.put(name, new InfoResultTest(th));

        if (stopWhenError) {
          return resultats;
        }

      }

    }

    return resultats;
  }

  /**
   * o1 valor esperat o2 valor generat
   */
  public static String[] compare(ResultatValidacio esperat, ResultatValidacio generat) {

    InformacioCertificat icEsperat = esperat.getInformacioCertificat();
    InformacioCertificat icGenerat = generat.getInformacioCertificat();

    if (icEsperat != null) {
      if (icGenerat == null) {
        return new String[] {"icEsperat != null i icGenerat ==null"};
      }
      return icEsperat.compareTo(icGenerat);
    }

    if (icGenerat != null) {
      // ic1 == null i ic2 !=null
      return new String[] { "icEsperat == null i icGenerat !=null" };
    } else {
      // ic1 es null i ic2 es null
      return null;
    }

  }

}
