package org.fundaciobit.pluginsib.validatecertificate.afirmacxf;

import java.io.StringReader;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.InfoCertificadoInfo;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.InfoCertificadoInfo.Campo;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.MensajeSalida;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.MensajeSalida.Respuesta.Excepcion;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.ResultadoValidacionInfo;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.ValidacionService;
import org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi.ValidacionWS;

/**
 * 
 * @author anadal
 * 
 */
@SuppressWarnings("restriction")
public class ValidaCertificat {

  public static final int MODE_VALIDACIO_SIMPLE = 0;
  public static final int MODE_VALIDACIO_AMB_REVOCACIO = 1;
  public static final int MODE_VALIDACIO_CADENA = 2;

  private static final JAXBContext jaxbContext;

  static {
    try {
      jaxbContext = JAXBContext.newInstance(MensajeSalida.class);
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }

  protected final Logger log = Logger.getLogger(getClass());

  private final String endPoint;
  private final String aplicacioId;

  private final int modeValidacio;
  
  private final boolean debug;

  private final ClientHandler clientHandler;

  private ValidaCertificat(String endPoint, String aplicacioId, int modeValidacio,
      ClientHandler clientHandler, boolean debug) {
    this.endPoint = endPoint;
    this.aplicacioId = aplicacioId;
    this.modeValidacio = modeValidacio;
    this.clientHandler = clientHandler;
    this.debug = debug;
  }

  public ValidaCertificat(String endPoint, String application_id, int modeValidacio,
      String username, String password, boolean debug) {
    this(endPoint, application_id, modeValidacio, new ClientHandlerUsernamePassword(username,
        password), debug);
  }

  public ValidaCertificat(String endPoint, String application_id, int modeValidacio,
      String keystoreLocation, String keystoreType, String keystorePassword,
      String keystoreCertAlias, String keystoreCertPassword, boolean debug) {

    this(endPoint, application_id, modeValidacio, new ClientHandlerCertificate(
        keystoreLocation, keystoreType, keystorePassword, keystoreCertAlias,
        keystoreCertPassword), debug);
  }

  public int getModeValidacio() {
    return modeValidacio;
  }

  public String getEndPoint() {
    return endPoint;
  }

  public String getAplicacioId() {
    return aplicacioId;
  }

  public ResultatValidacio validar(X509Certificate certificate, boolean obtenirDadesCertificat)
      throws Exception {
    return validar(certificate.getEncoded(), obtenirDadesCertificat);
  }

  private MensajeSalida getMensajeSalidaFromXml(String xml) throws Exception {
    Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
    MensajeSalida ms = (MensajeSalida) unMarshaller.unmarshal(new StringReader(xml));
    return ms;
  }

  public ResultatValidacio validar(byte[] data, boolean obtenirDadesCertificat)
      throws Exception {

    String certificatBase64 = Base64.getEncoder().encodeToString(data);

    String respostaXml = cridarValidarCertificado(certificatBase64, obtenirDadesCertificat, modeValidacio);

    MensajeSalida ms = getMensajeSalidaFromXml(respostaXml);

    Excepcion ex = ms.getRespuesta().getExcepcion();

    if (ex == null) {
      
      ResultadoValidacionInfo rvi = ms.getRespuesta().getResultadoProcesamiento().getResultadoValidacion();

      ResultatValidacio resultatValidacio = new ResultatValidacio();
      resultatValidacio.setResultatValidacioCodi(Integer.parseInt(rvi.getResultado()));
      resultatValidacio.setResultatValidacioDescripcio(rvi.getDescripcion());

      if (obtenirDadesCertificat) {
        InfoCertificadoInfo infoCert = ms.getRespuesta().getResultadoProcesamiento().getInfoCertificado();
        resultatValidacio.setInformacioCertificat(getDadesCertificat(infoCert));
      }
      return resultatValidacio;

    } else {

      log.error("Exception = " + ex);

      StringBuilder str = new StringBuilder();

      String codigoError = ex.getCodigoError();
      if (codigoError != null) {
        str.append("codigoError: ").append(codigoError);
      }

      String descripcionError = ex.getDescripcion();
      if (descripcionError != null) {
        if (str.length() != 0) {
          str.append("\n");
        }
        str.append("descripcionError: ").append(descripcionError);
      }

      String excepcionAsociada = ex.getExcepcionAsociada();
      if (excepcionAsociada != null) {
        if (str.length() != 0) {
          str.append("\n");
        }
        str.append("excepcionAsociada: ").append(excepcionAsociada);
      }

      throw new Exception(str.toString());
    }

  }

  private InformacioCertificat getDadesCertificat(InfoCertificadoInfo infoCert) {
    if (infoCert == null) {
      return null;
    }

    List<Campo> camps = infoCert.getCampo();
    
    Map<String, Object> map = new HashMap<>();
    
    for (Campo campo : camps) {
      map.put(campo.getIdCampo(), campo.getValorCampo());
    }

    return InfoCertificatUtils.processInfoCertificate(map);
  }

  // Cache
  private ValidacionWS api = null;

  private String cridarValidarCertificado(String certificatBase64,
      boolean obtenirDadesCertificat, int modeValidacio) throws Exception {

    if (api == null) {

      ValidacionService service = new ValidacionService(new java.net.URL(getEndPoint() + "?wsdl"));
      api = service.getValidarCertificado();
      
      // @firma no suporta. Veure https://github.com/GovernIB/pluginsib/issues/3
      Client client =  ClientProxy.getClient(api); 
      {
          HTTPConduit conduit = (HTTPConduit) client.getConduit();
          HTTPClientPolicy policy = new HTTPClientPolicy();
          policy.setAllowChunking(false);
          conduit.setClient(policy);
      }

      Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEndPoint());

      clientHandler.addSecureHeader(api);
      
    }

    String xmlPeticio = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<mensajeEntrada xmlns=\"http://afirmaws/ws/validacion\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:SchemaLocation=\"https://localhost/afirmaws/xsd/mvalidacion/ws.xsd\">"
        + "<peticion>ValidarCertificado</peticion>" + "<versionMsg>1.0</versionMsg>"
        + "<parametros>" + "<certificado><![CDATA[" + certificatBase64 + "]]></certificado>"
        + "<idAplicacion>" + aplicacioId + "</idAplicacion>" + "<modoValidacion>"
        + modeValidacio + "</modoValidacion>" + "<obtenerInfo>" + obtenirDadesCertificat
        + "</obtenerInfo>" + "</parametros>" + "</mensajeEntrada>";

    if (debug) {
      log.info("Petició: \n" + xmlPeticio);
    }

    String xmlResposta = api.validarCertificado(xmlPeticio);

    if (debug) {
      log.info("Resposta: \n" + xmlResposta);
    }

    return xmlResposta;
  }

}
