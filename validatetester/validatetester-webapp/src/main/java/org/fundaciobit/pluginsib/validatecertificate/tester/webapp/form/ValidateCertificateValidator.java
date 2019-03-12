package org.fundaciobit.pluginsib.validatecertificate.tester.webapp.form;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author anadal
 *
 */
@Component
public class ValidateCertificateValidator implements Validator {

  protected final Logger log = Logger.getLogger(getClass());

  public ValidateCertificateValidator() {
    super();
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return ValidateCertificateForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    CommonsMultipartFile document = ((ValidateCertificateForm) target).getDocument();

    if ((document == null || document.isEmpty())) {
      errors.rejectValue("document", "genapp.validation.required",
          new String[] { "document" }, null);
    }

  }

}
