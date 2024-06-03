package org.fundaciobit.pluginsib.validatecertficate.test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.pluginsib.validatecertificate.ICertificatePlugin;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.jboss.logging.Logger;

import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;

/**
 * 
 * @author anadal
 *
 */
public class TestCertificate {

    public Logger log = Logger.getLogger(TestCertificate.class);

    protected Map<String, InfoResultTest> executeTests(ICertificatePlugin plugin, Properties testProp, File resultsDir,
            File expectedDir, boolean printResult, boolean stopWhenError) throws Exception, FileNotFoundException {
        String testsStr = testProp.getProperty("tests");

        String[] tests = testsStr.split(",");

        System.out.println("");

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

                    List<Certificate> cc = CertificateUtils.readCertificatesOfKeystore(new FileInputStream(certFile),
                            passwordks);
                    if (cc == null || cc.size() == 0) {
                        throw new Exception("Certificat amb id " + name + " esta buit.");
                    }
                    certificat = (X509Certificate) cc.get(0);

                }

                ResultatValidacio rv = plugin.getInfoCertificate(certificat);

                String filename = tests[i] + "_" + certFile.getName() + ".xml";
                File file = new File(resultsDir, filename);

                // output pretty printed
                //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                XMLEncoder jaxbMarshaller = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));

                jaxbMarshaller.writeObject(rv);
                jaxbMarshaller.close();

                File expectedFile = new File(expectedDir, filename);

                String[] errorsComparacio = null;
                String warning = null;

                ResultatValidacio expected = null;
                if (expectedFile.exists()) {
                    // Podem Comparar
                    XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(expectedFile)));

                    expected = (ResultatValidacio) d.readObject();

                    errorsComparacio = compare(expected, rv);

                    if (errorsComparacio != null) {
                        if (printResult) {
                            System.out.flush();
                            System.err.flush();
                            System.err.println();
                            System.err.println();
                            System.err.println("El resultat del test " + name + " no és l'esperat. Diferències("
                                    + errorsComparacio.length + ") [ esperat | retornat ]: ");

                            for (String e : errorsComparacio) {
                                System.err.println("  + " + e);
                            }
                            System.err.println();
                            System.err.println();
                            System.err.flush();
                        }
                    }

                } else {
                    warning = " No existeix fitxer per comparar dins la carpeta expected ("
                            + expectedFile.getAbsolutePath() + ")";
                }

                resultats.put(name, new InfoResultTest(expected, rv, errorsComparacio, warning));

                if (printResult) {

                    if (rv.getResultatValidacioCodi() != ResultatValidacio.RESULTAT_VALIDACIO_OK) {
                        System.err.println("Error en la validació del certificat " + name + " amb codi "
                                + rv.getResultatValidacioCodi());
                        System.err.println("Resultat Validacio Codi: " + rv.getResultatValidacioCodi());
                        System.err.println("Resultat Validacio Desc.: " + rv.getResultatValidacioDescripcio() + "\n");
                        System.out.println(rv.getInformacioCertificat());

                    } else {
                        // OK
                        System.out.println(rv.toString());
                    }

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
                return new String[] { "icEsperat != null i icGenerat ==null" };
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
