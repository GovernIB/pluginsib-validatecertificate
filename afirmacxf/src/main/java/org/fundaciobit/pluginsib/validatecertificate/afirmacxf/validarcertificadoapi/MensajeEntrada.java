
package org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="peticion"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://afirmaws/ws/validacion}CadenaSinEspacios"&amp;gt;
 *               &amp;lt;enumeration value="ValidarCertificado"/&amp;gt;
 *               &amp;lt;enumeration value="ObtenerInfoCertificado"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="versionMsg" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="parametros"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;all&amp;gt;
 *                   &amp;lt;element name="certificado" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&amp;gt;
 *                   &amp;lt;element name="idAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *                   &amp;lt;element name="modoValidacion" minOccurs="0"&amp;gt;
 *                     &amp;lt;simpleType&amp;gt;
 *                       &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&amp;gt;
 *                         &amp;lt;minInclusive value="0"/&amp;gt;
 *                         &amp;lt;maxInclusive value="2"/&amp;gt;
 *                       &amp;lt;/restriction&amp;gt;
 *                     &amp;lt;/simpleType&amp;gt;
 *                   &amp;lt;/element&amp;gt;
 *                   &amp;lt;element name="obtenerInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *                 &amp;lt;/all&amp;gt;
 *               &amp;lt;/restriction&amp;gt;
 *             &amp;lt;/complexContent&amp;gt;
 *           &amp;lt;/complexType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "peticion",
    "versionMsg",
    "parametros"
})
@XmlRootElement(name = "mensajeEntrada")
public class MensajeEntrada {

    @XmlElement(required = true)
    protected String peticion;
    @XmlElement(required = true)
    protected String versionMsg;
    @XmlElement(required = true)
    protected MensajeEntrada.Parametros parametros;

    /**
     * Gets the value of the peticion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeticion() {
        return peticion;
    }

    /**
     * Sets the value of the peticion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeticion(String value) {
        this.peticion = value;
    }

    /**
     * Gets the value of the versionMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionMsg() {
        return versionMsg;
    }

    /**
     * Sets the value of the versionMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionMsg(String value) {
        this.versionMsg = value;
    }

    /**
     * Gets the value of the parametros property.
     * 
     * @return
     *     possible object is
     *     {@link MensajeEntrada.Parametros }
     *     
     */
    public MensajeEntrada.Parametros getParametros() {
        return parametros;
    }

    /**
     * Sets the value of the parametros property.
     * 
     * @param value
     *     allowed object is
     *     {@link MensajeEntrada.Parametros }
     *     
     */
    public void setParametros(MensajeEntrada.Parametros value) {
        this.parametros = value;
    }


    /**
     * &lt;p&gt;Java class for anonymous complex type.
     * 
     * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
     * 
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     *   &amp;lt;complexContent&amp;gt;
     *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     *       &amp;lt;all&amp;gt;
     *         &amp;lt;element name="certificado" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&amp;gt;
     *         &amp;lt;element name="idAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
     *         &amp;lt;element name="modoValidacion" minOccurs="0"&amp;gt;
     *           &amp;lt;simpleType&amp;gt;
     *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&amp;gt;
     *               &amp;lt;minInclusive value="0"/&amp;gt;
     *               &amp;lt;maxInclusive value="2"/&amp;gt;
     *             &amp;lt;/restriction&amp;gt;
     *           &amp;lt;/simpleType&amp;gt;
     *         &amp;lt;/element&amp;gt;
     *         &amp;lt;element name="obtenerInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
     *       &amp;lt;/all&amp;gt;
     *     &amp;lt;/restriction&amp;gt;
     *   &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Parametros {

        @XmlElement(required = true)
        protected byte[] certificado;
        @XmlElement(required = true)
        protected String idAplicacion;
        protected Integer modoValidacion;
        protected Boolean obtenerInfo;

        /**
         * Gets the value of the certificado property.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getCertificado() {
            return certificado;
        }

        /**
         * Sets the value of the certificado property.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setCertificado(byte[] value) {
            this.certificado = value;
        }

        /**
         * Gets the value of the idAplicacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdAplicacion() {
            return idAplicacion;
        }

        /**
         * Sets the value of the idAplicacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdAplicacion(String value) {
            this.idAplicacion = value;
        }

        /**
         * Gets the value of the modoValidacion property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getModoValidacion() {
            return modoValidacion;
        }

        /**
         * Sets the value of the modoValidacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setModoValidacion(Integer value) {
            this.modoValidacion = value;
        }

        /**
         * Gets the value of the obtenerInfo property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isObtenerInfo() {
            return obtenerInfo;
        }

        /**
         * Sets the value of the obtenerInfo property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setObtenerInfo(Boolean value) {
            this.obtenerInfo = value;
        }

    }

}
