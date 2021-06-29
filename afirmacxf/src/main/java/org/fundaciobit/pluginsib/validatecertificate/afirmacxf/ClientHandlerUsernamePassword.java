package org.fundaciobit.pluginsib.validatecertificate.afirmacxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.ValidacionWS;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 * 
 */
public class ClientHandlerUsernamePassword extends ClientHandler {

  private final String username;
  private final String password;

  public ClientHandlerUsernamePassword(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public void addSecureHeader(ValidacionWS api) {

    Client client = ClientProxy.getClient(api);
    Endpoint cxfEndpoint = client.getEndpoint();

    Map<String, Object> outProps = new HashMap<String, Object>();

    outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
    outProps.put(WSHandlerConstants.USER, username);

    // for hashed password use:
    outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
    // Password type : plain text
    // outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);

    outProps.put(WSHandlerConstants.MUST_UNDERSTAND, "false");

    outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_NONCE, "true");
    outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_CREATED, "true");

    // Callback used to retrieve password for given user.
    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new ClientPasswordCallback(password));

    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
    cxfEndpoint.getOutInterceptors().add(wssOut);
  }

  public static class ClientPasswordCallback implements CallbackHandler {
    final String password;

    public ClientPasswordCallback(String password) {
      super();
      this.password = password;
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
      WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
      pc.setPassword(password);
    }

  }

}
