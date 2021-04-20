package org.fundaciobit.pluginsib.validatecertificate.fake;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.pluginsib.validatecertificate.ICertificatePlugin;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.validatecertficate.test.InfoResultTest;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class FakeTestCertificate extends
    org.fundaciobit.pluginsib.validatecertficate.test.TestCertificate {

  public static void main(String[] args) {

    new FakeTestCertificate().testValidateCertificate();

  }

  public void testValidateCertificate() {
    try {

      final String basePropertiesKey = "org.fundaciobit.validatecertificate.1.";

      final Class<?> c = FakeCertificatePlugin.class;

      Properties pluginProp = FileUtils.readPropertiesFromFile(new File("config.properties"));

      ICertificatePlugin plugin = (ICertificatePlugin) PluginsManager.instancePluginByClass(c,
          basePropertiesKey, pluginProp);

      File baseDir = new File("../results/");
      baseDir.mkdirs();

      Properties testProp = FileUtils.readPropertiesFromFile(new File(
          "../certificats/test.properties"));

      File expectedDir = new File(baseDir, "expected");

      File resultsDir = new File(baseDir.getAbsolutePath(), plugin.getClass().getSimpleName());
      resultsDir.mkdirs();

      final boolean printResults = false;

      final boolean stopWhenError = false;

      Map<String, InfoResultTest> results = executeTests(plugin, testProp, resultsDir, expectedDir, printResults, stopWhenError);
      
      
      
      for(String test : results.keySet()) {
        
        InfoResultTest res = results.get(test);
        
        System.out.println("\n\n ========= TEST[" + test + "] =============");
        
        if (res.getWarning() != null) {
          System.err.println(" AVIS: " + res.getWarning());
          
        }
        
        if (res.getErrorsComparacio() != null) {
          // Tornam a passar-ho eliminant els camps que sabem que fake no pot esbrinar
          
          ResultatValidacio expected = res.getExpected();
          
          if (expected != null) {
            
            InformacioCertificat ic = expected.getInformacioCertificat();
            ic.setCertificatQualificat(null);
            ic.setClassificacio(-1);
            ic.setClassificacioEidas(null);
            ic.setPoliticaID(null);
            ic.setPolitica(null);
            ic.setPoliticaVersio(null);
            ic.setTipusCertificat(null);
            ic.setSubject(null);
            ic.setEntitatSubscriptoraNif(null);
            ic.setCreatAmbUnDispositiuSegur(null);
            ic.setDenominacioSistemaComponent(null);
            
            String[] errorsComparacio = compare(expected, res.getResultatValidacio());
            
            res.setErrorsComparacio(errorsComparacio);
            
          }
          
          
          if (res.getErrorsComparacio() == null) {
            // OK
            System.out.println(res.getResultatValidacio().toString());
          } else {
            //System.err.println(" ERROR: " + res.getError());
            System.err.println("El resultat del test " + test
                + " no és l'esperat. Diferències (" + res.getErrorsComparacio().length + ") : ");
            for(String e: res.getErrorsComparacio()) {
              System.err.println("  + " + e);
            }
            System.err.flush();
          } 
          
          
        } else if (res.getExcepcio() != null) {
          System.err.println(" EXCEPCIO: " + res.getExcepcio().getMessage());
          res.getExcepcio().printStackTrace(System.err);
        } else {
          System.out.println(res.getResultatValidacio().toString());
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
