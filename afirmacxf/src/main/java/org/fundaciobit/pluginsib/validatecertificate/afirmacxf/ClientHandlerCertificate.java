package org.fundaciobit.pluginsib.validatecertificate.afirmacxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.ValidacionWS;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 */
public class ClientHandlerCertificate extends ClientHandler {

  private final String keystoreLocation;
  private final String keystoreType;
  private final String keystorePassword;
  private final String keystoreCertAlias;
  private final String keystoreCertPassword;

  public ClientHandlerCertificate(String keystoreLocation, String keystoreType,
                                  String keystorePassword, String keystoreCertAlias, String keystoreCertPassword) {
    this.keystoreLocation = keystoreLocation;
    this.keystoreType = keystoreType;
    this.keystorePassword = keystorePassword;
    this.keystoreCertAlias = keystoreCertAlias;
    this.keystoreCertPassword = keystoreCertPassword;
  }

  @Override
  public void addSecureHeader(ValidacionWS api) {

    Client client = ClientProxy.getClient(api);
    Endpoint cxfEndpoint = client.getEndpoint();

    Map<String, Object> outProps = new HashMap<>();

    outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);

    outProps.put(WSHandlerConstants.USER, keystoreCertAlias);

    Properties cryptoProperties = new Properties();
    cryptoProperties.put("org.apache.ws.security.crypto.provider", "org.apache.ws.security.components.crypto.Merlin");
    cryptoProperties.put("org.apache.ws.security.crypto.merlin.file", keystoreLocation);
    cryptoProperties.put("org.apache.ws.security.crypto.merlin.keystore.password", keystorePassword);
    cryptoProperties.put("org.apache.ws.security.crypto.merlin.keystore.type", keystoreType); // "pkcs12"
    cryptoProperties.put("org.apache.ws.security.crypto.merlin.keystore.alias", keystoreCertAlias);

    outProps.put("cryptoProperties", cryptoProperties); // ?????

    outProps.put(WSHandlerConstants.SIG_PROP_REF_ID, "cryptoProperties");
    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new ClientCallbackSSL(keystoreCertPassword));

    outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");

    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

    cxfEndpoint.getOutInterceptors().add(wssOut);
  }

  public static class ClientCallbackSSL implements CallbackHandler {

    private final String password;

    public ClientCallbackSSL(String password) {
      this.password = password;
    }

    public void handle(Callback[] callbacks) throws IOException {
      for (Callback callback : callbacks) {
        WSPasswordCallback pwcb = (WSPasswordCallback) callback;
        int usage = pwcb.getUsage();
        if (usage == WSPasswordCallback.DECRYPT || usage == WSPasswordCallback.SIGNATURE) {
          pwcb.setPassword(password);
        }
      }
    }
  }

}
