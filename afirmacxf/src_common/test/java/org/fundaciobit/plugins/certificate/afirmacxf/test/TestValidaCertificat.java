package org.fundaciobit.plugins.certificate.afirmacxf.test;

import java.io.File;
import java.util.Properties;


import org.fundaciobit.plugins.certificate.ICertificatePlugin;
import org.fundaciobit.plugins.certificate.afirmacxf.AfirmaCxfCertificatePlugin;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class TestValidaCertificat extends
    org.fundaciobit.pluginsib.validatecertficate.test.TestCertificate {

  public static void main(String[] args) {

    new TestValidaCertificat().testValidateCertificate();

  }

  public void testValidateCertificate() {
    try {
      
      final String basePropertiesKey = "org.fundaciobit.validatecertificate.1.";
      
      final Class<?> c = AfirmaCxfCertificatePlugin.class;

      Properties pluginProp = FileUtils.readPropertiesFromFile(new File("config.properties"));
      
      ICertificatePlugin plugin = (ICertificatePlugin) PluginsManager.instancePluginByClass(c,
          basePropertiesKey, pluginProp);

      File baseDir = new File("../results/");
      baseDir.mkdirs();
      
      Properties testProp = FileUtils.readPropertiesFromFile(new File("../certificats/test.properties"));

      File expectedDir = new File(baseDir, "expected");

      File resultsDir = new File(baseDir.getAbsolutePath(), plugin.getClass().getSimpleName());
      resultsDir.mkdirs();

      final boolean printResuts = true;

      final boolean stopWhenError = true;

      executeTests(plugin, testProp, resultsDir, expectedDir, printResuts, stopWhenError);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
