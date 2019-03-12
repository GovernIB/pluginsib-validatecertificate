package org.fundaciobit.pluginsib.validatecertificate.tester.ejb;

import java.security.cert.X509Certificate;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.plugins.certificate.ResultatValidacio;
import org.fundaciobit.pluginsib.validatecertificate.tester.utils.Plugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ValidateCertificateLocal {
  
  public static final String JNDI_NAME = "validatecertificate/ValidateCertificateEJB/local";
  
  
  public ResultatValidacio validate(X509Certificate certificat,
      long pluginID) throws Exception;
  
  public List<Plugin> getPlugins() throws Exception;
  
  
  public void esborrarCachePlugins() throws Exception;


}
