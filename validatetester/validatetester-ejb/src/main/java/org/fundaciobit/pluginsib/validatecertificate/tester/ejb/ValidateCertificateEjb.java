package org.fundaciobit.pluginsib.validatecertificate.tester.ejb;

import java.security.cert.X509Certificate;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.validatecertificate.ICertificatePlugin;
import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.fundaciobit.pluginsib.validatecertificate.tester.utils.Plugin;
import org.fundaciobit.pluginsib.validatecertificate.tester.utils.ValidateCertificatePluginManager;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "ValidateCertificateEJB")
public class ValidateCertificateEjb implements ValidateCertificateLocal {

  protected Logger log = Logger.getLogger(this.getClass());


  @Override
  public ResultatValidacio validate(X509Certificate certificat,
      long pluginID) throws Exception {

    ValidateCertificatePluginManager vspm = ValidateCertificatePluginManager.getInstance();

    // XYZ ZZZ
    ICertificatePlugin plugin = vspm.getInstanceByPluginID(pluginID);

   

    ResultatValidacio valResponse = plugin.getInfoCertificate(certificat);

    return valResponse;

  }

  @Override
  public List<Plugin> getPlugins() throws Exception {

   
    List<Plugin> plugins = ValidateCertificatePluginManager.getInstance().getAllPlugins();
    if (plugins == null || plugins.size() == 0) {
      String msg = "S'ha produit un error llegint els plugins o no se n'han definit.";
      throw new Exception(msg);
    }

    return plugins;

  }

  @Override
  public void esborrarCachePlugins() throws Exception {
    ValidateCertificatePluginManager.getInstance().clearCache();
  }

}
