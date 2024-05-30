package org.fundaciobit.pluginsib.validatecertificate.afirmacxf.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.pluginsib.validatecertficate.test.InfoResultTest;
import org.fundaciobit.pluginsib.validatecertficate.test.TestCertificate;
import org.fundaciobit.pluginsib.validatecertificate.ICertificatePlugin;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.AfirmaCxfCertificatePlugin;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.junit.Test;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class AfirmaCXFTestCertificate extends TestCertificate {

    public static void main(String[] args) {

        try {
            Enumeration<URL> resEnum;
            resEnum = AfirmaCXFTestCertificate.class.getClassLoader().getResources("javax/xml/namespace/QName.class");

            ArrayList<URL> resources = Collections.list(resEnum);
            for (Iterator<URL> iterator = resources.iterator(); iterator.hasNext();) {
                URL url = (URL) iterator.next();
                System.out.println(url);
            }

            AfirmaCXFTestCertificate tester = new AfirmaCXFTestCertificate();
            tester.testValidateCertificate();
            //tester.testSearchCertificatesWithAltresValors();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    
    public void testSearchCertificatesWithAltresValors() {
        try {

            ICertificatePlugin plugin = getPlugin();

            File baseDir = new File("../results/");
            baseDir.mkdirs();

            Properties testProp = getTestProperties();

            File expectedDir = new File(baseDir, "expected");

            File resultsDir = new File(baseDir.getAbsolutePath(), plugin.getClass().getSimpleName());
            resultsDir.mkdirs();

            final boolean printResults = false;
            final boolean stopWhenError = false;

            Map<String, InfoResultTest> results = executeTests(plugin, testProp, resultsDir, expectedDir, printResults,
                    stopWhenError);

            for (Map.Entry<String, InfoResultTest> entry : results.entrySet()) {
                String key = entry.getKey();
                InfoResultTest val = entry.getValue();
                if (val != null && val.getResultatValidacio() != null
                        && val.getResultatValidacio().getInformacioCertificat() != null) {
                    Map<String, String> altresValors = val.getResultatValidacio().getInformacioCertificat()
                            .getAltresValors();
                    if (altresValors != null && altresValors.size() != 0) {
                        for (Map.Entry<String, String> altreValors : altresValors.entrySet()) {
                            String id = altreValors.getKey();
                            String valor = altreValors.getValue();

                            System.out.println("CERTIFICAT[" + key + "]: " + id + " => " + valor);
                        }
                    }
                }
            }

            System.out.println("FINAL");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testValidateCertificate() {
        try {

            ICertificatePlugin plugin = getPlugin();

            File baseDir = new File("../results/");
            baseDir.mkdirs();

            Properties testProp = getTestProperties();

            File expectedDir = new File(baseDir, "expected");

            File resultsDir = new File(baseDir.getAbsolutePath(), plugin.getClass().getSimpleName());
            resultsDir.mkdirs();

            final boolean printResuts = true;
            final boolean stopWhenError = false;

            executeTests(plugin, testProp, resultsDir, expectedDir, printResuts, stopWhenError);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected Properties getTestProperties() throws Exception, FileNotFoundException, IOException {
        File f = new File("../certificats/test.properties");

        if (!f.exists()) {
            throw new Exception("S'ha de definir un fitxer " + f.getAbsolutePath()
                    + ". Pot trobar un exemple en el fitxer test.properties_sample");
        }

        Properties testProp = FileUtils.readPropertiesFromFile(f);
        return testProp;
    }

    protected ICertificatePlugin getPlugin() throws FileNotFoundException, IOException {
        final String basePropertiesKey = "org.fundaciobit.validatecertificate.1.";

        final Class<?> c = AfirmaCxfCertificatePlugin.class;

        Properties pluginProp = FileUtils.readPropertiesFromFile(new File("config.properties"));

        ICertificatePlugin plugin = (ICertificatePlugin) PluginsManager.instancePluginByClass(c, basePropertiesKey,
                pluginProp);
        return plugin;
    }

}
