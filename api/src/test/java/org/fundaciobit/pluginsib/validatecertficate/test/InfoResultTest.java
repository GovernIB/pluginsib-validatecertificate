package org.fundaciobit.pluginsib.validatecertficate.test;

import org.fundaciobit.plugins.certificate.ResultatValidacio;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class InfoResultTest {

  final ResultatValidacio expected;
  
  final ResultatValidacio resultatValidacio;

  String[] errorsComparacio;
  
  String warning;

  Throwable excepcio;
  
  
  

  private InfoResultTest(ResultatValidacio expected, ResultatValidacio resultatValidacio,
      String[] errorsComparacio, String warning, Throwable excepcio) {
    super();
    this.expected = expected;
    this.resultatValidacio = resultatValidacio;
    this.errorsComparacio = errorsComparacio;
    this.warning = warning;
    this.excepcio = excepcio;
  }

  public InfoResultTest(ResultatValidacio expected, ResultatValidacio resultatValidacio, String[] errorsComparacio, String warning) {
    this(expected, resultatValidacio,
         errorsComparacio, warning, null);
    
  }

  public InfoResultTest(Throwable excepcio) {
    this(null, null,
        null, null, null);
  }

  public ResultatValidacio getResultatValidacio() {
    return resultatValidacio;
  }

  public String[] getErrorsComparacio() {
    return errorsComparacio;
  }

  public Throwable getExcepcio() {
    return excepcio;
  }

  public String getWarning() {
    return warning;
  }

  public ResultatValidacio getExpected() {
    return expected;
  }

  public void setErrorsComparacio(String[] errorsComparacio) {
    this.errorsComparacio = errorsComparacio;
  }

  public void setWarning(String warning) {
    this.warning = warning;
  }

  public void setExcepcio(Throwable excepcio) {
    this.excepcio = excepcio;
  }

}
