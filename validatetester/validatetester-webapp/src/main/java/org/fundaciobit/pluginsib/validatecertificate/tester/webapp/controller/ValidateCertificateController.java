package org.fundaciobit.pluginsib.validatecertificate.tester.webapp.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.validatecertificate.tester.ejb.ValidateCertificateLocal;
import org.fundaciobit.pluginsib.validatecertificate.tester.utils.Plugin;
import org.fundaciobit.pluginsib.validatecertificate.tester.webapp.form.ValidateCertificateForm;
import org.fundaciobit.pluginsib.validatecertificate.tester.webapp.form.ValidateCertificateValidator;
import org.fundaciobit.pluginsib.validatecertificate.tester.webapp.utils.HtmlUtils;
import org.fundaciobit.pluginsib.validatecertificate.tester.webapp.utils.InfoGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ValidateCertificateController.CONTEXTWEB)
@SessionAttributes(types = { org.fundaciobit.pluginsib.validatecertificate.tester.webapp.form.ValidateCertificateForm.class })
public class ValidateCertificateController {

  /** Logger for this class and subclasses */
  protected final Logger log = Logger.getLogger(getClass());

  public static final String CONTEXTWEB = "/common/validatecertificate";
  
  

  public static final String SESSION_INFO = "VALIDATECERTIFICATESESSION";
  
  
  @EJB(mappedName = ValidateCertificateLocal.JNDI_NAME)
  protected ValidateCertificateLocal validateCertificateEjb;

  //XYZ ZZZ public static final String PDF_MIME_TYPE = "application/pdf";

  @Autowired
  protected ValidateCertificateValidator validatecertificateValidator;

  /**
   * 
   */
  public ValidateCertificateController() {
    super();
  }
  
  
  @ModelAttribute("plugins")
  public List<Plugin> getPlugins() throws Exception {
    return validateCertificateEjb.getPlugins();
  }


  @RequestMapping(value = "/form", method = RequestMethod.GET)
  public ModelAndView validateCertificateGet(HttpServletRequest request) throws Exception {

    request.removeAttribute(SESSION_INFO);

    ValidateCertificateForm form = new ValidateCertificateForm();
   
    ModelAndView mav = new ModelAndView("validateCertificateForm");
    mav.addObject(form);

    return mav;

  }
  

  @RequestMapping(value = "/form", method = RequestMethod.POST)
  public ModelAndView validateCertificatePost(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute ValidateCertificateForm form, BindingResult result) throws Exception {
    
    
    if (request.getParameter("esborrarcache") != null){
      validateCertificateEjb.esborrarCachePlugins();
      log.info("XXXXX   ===>   ");
      org.fundaciobit.pluginsib.validatecertificate.tester.webapp.utils.HtmlUtils.saveMessageSuccess(request, "Netejada Cache de Plugins");
      
      ModelAndView mav = new ModelAndView("validateCertificateForm");
      mav.addObject(form);
      return mav;
    }
    

    validatecertificateValidator.validate(form, result);

    if (result.hasErrors()) {

      log.warn("Validador té errors !!!!");

      ModelAndView mav = new ModelAndView("validateCertificateForm");
      mav.addObject(form);
      return mav;
    }

    CommonsMultipartFile document = form.getDocument();
    

    @SuppressWarnings("unused")
    final String langUI = form.getLangUI();

    try {

        // Document
      
        byte[] documentData = processFile(document);
        
       
        X509Certificate certificate = CertificateUtils.decodeCertificate(new ByteArrayInputStream(documentData));

        ResultatValidacio valResponse = validateCertificateEjb.validate(certificate, form.getPluginID());
              
        
        
        // XYZ ZZZ
        System.out.println("LLEGENDA: CERTIFICATE_VALID = 0 | CERTIFICATE_ERROR != 0");
        
        // ResultatValidacio.RESULTAT_VALIDACIO_OK = 0;
        System.out.println(" ESTAT VALIDACIO: " + valResponse.getResultatValidacioCodi());
        
        
        System.out.println(" ESTAT DESCRIPCIO: " + valResponse.getResultatValidacioDescripcio());
        
        InformacioCertificat ic = valResponse.getInformacioCertificat();
        
        System.out.println(" INFO CERTIFICAT: " + ic.toString());




        return finalValidacioDCertificateServer(request, response, certificate, valResponse);

      
    
      
    } catch (Throwable e) {

      String msg = "Error desconegut processant entrada de dades o inicialitzant la validació de la firma: "
          + e.getMessage();

      log.error(msg, e);

      org.fundaciobit.pluginsib.validatecertificate.tester.webapp.utils.HtmlUtils.saveMessageError(request, msg);

      ModelAndView mav = new ModelAndView("validateCertificateForm");
      mav.addObject(form);

      return mav;
    }

  }


  
  

  protected byte[] processFile(CommonsMultipartFile cmf) throws Exception {

    if (cmf == null || cmf.isEmpty()) {
      return null;
    }

    InputStream is = cmf.getInputStream();    
    byte[] dataFitxer = IOUtils.toByteArray(is);
    is.close();
    
    return dataFitxer;
  }





  public ModelAndView finalValidacioDCertificateServer(HttpServletRequest request,
      HttpServletResponse response, X509Certificate validateRequest,
      ResultatValidacio validateResponse) throws Exception {

   
    int status = validateResponse.getResultatValidacioCodi();

    if (status != ResultatValidacio.RESULTAT_VALIDACIO_OK) {
      String desc = validateResponse.getResultatValidacioDescripcio();
      if (desc == null) {
        desc = "DESCONEGUT";
      }
      String msg = "Error desconegut durant la validació de la firma (Codi " + status + " ): " + desc;
      log.error(msg);
      HtmlUtils.saveMessageError(request, msg);
      return new ModelAndView(new RedirectView("/", true));
    } else {
      // OK

      InfoGlobal infoGlobal = new InfoGlobal(validateRequest, validateResponse);

      request.getSession().setAttribute(SESSION_INFO, infoGlobal);

      ModelAndView mav = new ModelAndView("validateCertificateFinal");
      mav.addObject("infoGlobal", infoGlobal);
      return mav;

    } // Final Case Certificate OK
   

  }


  @InitBinder("validateCertificateForm")
  public void initBinder(WebDataBinder binder) {

    binder.setValidator(this.validatecertificateValidator);

    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

  }

}
