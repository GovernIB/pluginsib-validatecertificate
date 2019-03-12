package org.fundaciobit.plugins.certificate.afirmacxf.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.Properties;

import org.fundaciobit.plugins.certificate.ICertificatePlugin;
import org.fundaciobit.plugins.certificate.ResultatValidacio;
import org.fundaciobit.plugins.certificate.afirmacxf.AfirmaCxfCertificatePlugin;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

/**
 * 
 * @author anadal
 * 
 */
public class TestValidaCertificat {

  public static void main(String[] args) {
       
    testValidateCertificate();

  }

  public static void testValidateCertificate() {
    try {

      final String basePropertiesKey = "org.fundaciobit.validatecertificate.1.";
      Properties testProp = FileUtils.readPropertiesFromFile(new File("test.properties"));
      Properties pluginProp = FileUtils.readPropertiesFromFile(new File("config.properties"));
      
      File pkcs7 = new File(testProp.getProperty("certificate.file"));
      //byte[] data = getBytesFromFile(pkcs7);

      X509Certificate certificat = CertificateUtils.decodeCertificate(new FileInputStream(pkcs7));
      
      
      Class<?> c = AfirmaCxfCertificatePlugin.class;
      
      ICertificatePlugin plugin = (ICertificatePlugin)PluginsManager.instancePluginByClass(c, basePropertiesKey, pluginProp);

      ResultatValidacio rv = plugin.getInfoCertificate(certificat);

      System.out.println(" =============== OK ================");
      System.out.println(rv.toString());

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // Returns the contents of the file in a byte array.
  public static byte[] getBytesFromFile(File file) throws IOException {
    InputStream is = new FileInputStream(file);

    // Get the size of the file
    long length = file.length();

    // You cannot create an array using a long type.
    // It needs to be an int type.
    // Before converting to an int type, check
    // to ensure that file is not larger than Integer.MAX_VALUE.
    if (length > Integer.MAX_VALUE) {
      // File is too large
    }

    // Create the byte array to hold the data
    byte[] bytes = new byte[(int) length];

    // Read in the bytes
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length
        && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
      offset += numRead;
    }

    // Ensure all the bytes have been read in
    if (offset < bytes.length) {
      throw new IOException("Could not completely read file " + file.getName());
    }

    // Close the input stream and return bytes
    is.close();
    return bytes;
  }

}
