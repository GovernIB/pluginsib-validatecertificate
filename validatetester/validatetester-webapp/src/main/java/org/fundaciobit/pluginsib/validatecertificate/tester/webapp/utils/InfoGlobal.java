package org.fundaciobit.pluginsib.validatecertificate.tester.webapp.utils;

import java.security.cert.X509Certificate;

import org.fundaciobit.plugins.certificate.ResultatValidacio;

/**
 * 
 * @author anadal
 *
 */
public class InfoGlobal {

  protected final X509Certificate validateRequest;
  protected final ResultatValidacio validateResponse;
  
  public InfoGlobal(X509Certificate validateRequest, ResultatValidacio validateResponse) {
    super();
    this.validateRequest = validateRequest;
    this.validateResponse = validateResponse;
  }

  public X509Certificate getValidateRequest() {
    return validateRequest;
  }

  public ResultatValidacio getValidateResponse() {
    return validateResponse;
  }
  

}
