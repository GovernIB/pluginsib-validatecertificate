package org.fundaciobit.pluginsib.validatecertificate.afirmacxf;

import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.ValidacionWS;

/**
 * 
 * @author anadal
 *
 */
public abstract class ClientHandler {

  public abstract void addSecureHeader(ValidacionWS api);
  
}
