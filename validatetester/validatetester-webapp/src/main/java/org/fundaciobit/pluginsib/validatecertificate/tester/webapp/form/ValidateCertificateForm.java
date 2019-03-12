package org.fundaciobit.pluginsib.validatecertificate.tester.webapp.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author anadal
 * 
 */
public class ValidateCertificateForm {

  protected long pluginID;

  protected String langUI;

  protected CommonsMultipartFile document;

  public ValidateCertificateForm() {
  }

  public String getLangUI() {
    return langUI;
  }

  public void setLangUI(String langUI) {
    this.langUI = langUI;
  }

  public CommonsMultipartFile getDocument() {
    return document;
  }

  public void setDocument(CommonsMultipartFile document) {
    this.document = document;
  }

  public long getPluginID() {
    return pluginID;
  }

  public void setPluginID(long pluginID) {
    this.pluginID = pluginID;
  }

}
