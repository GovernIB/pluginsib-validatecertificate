
package org.fundaciobit.pluginsib.validatecertificate.afirmacxf.validarcertificadoapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ValidacionCadenaInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ValidacionCadenaInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codigoResultado" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="descResultado" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="errorCertificado" maxOccurs="unbounded" minOccurs="0"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;sequence&amp;gt;
 *                   &amp;lt;element name="idCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *                   &amp;lt;element name="ValidacionSimple" type="{http://afirmaws/ws/validacion}ValidacionSimpleInfo"/&amp;gt;
 *                   &amp;lt;element name="ValidacionEstado" type="{http://afirmaws/ws/validacion}ValidacionEstadoInfo"/&amp;gt;
 *                 &amp;lt;/sequence&amp;gt;
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
@XmlType(name = "ValidacionCadenaInfo", propOrder = {
    "codigoResultado",
    "descResultado",
    "errorCertificado"
})
public class ValidacionCadenaInfo {

    @XmlElement(required = true)
    protected String codigoResultado;
    @XmlElement(required = true)
    protected String descResultado;
    protected List<ValidacionCadenaInfo.ErrorCertificado> errorCertificado;

    /**
     * Gets the value of the codigoResultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoResultado() {
        return codigoResultado;
    }

    /**
     * Sets the value of the codigoResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoResultado(String value) {
        this.codigoResultado = value;
    }

    /**
     * Gets the value of the descResultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescResultado() {
        return descResultado;
    }

    /**
     * Sets the value of the descResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescResultado(String value) {
        this.descResultado = value;
    }

    /**
     * Gets the value of the errorCertificado property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the errorCertificado property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getErrorCertificado().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ValidacionCadenaInfo.ErrorCertificado }
     * 
     * 
     */
    public List<ValidacionCadenaInfo.ErrorCertificado> getErrorCertificado() {
        if (errorCertificado == null) {
            errorCertificado = new ArrayList<ValidacionCadenaInfo.ErrorCertificado>();
        }
        return this.errorCertificado;
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
     *       &amp;lt;sequence&amp;gt;
     *         &amp;lt;element name="idCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
     *         &amp;lt;element name="ValidacionSimple" type="{http://afirmaws/ws/validacion}ValidacionSimpleInfo"/&amp;gt;
     *         &amp;lt;element name="ValidacionEstado" type="{http://afirmaws/ws/validacion}ValidacionEstadoInfo"/&amp;gt;
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
        "idCertificado",
        "validacionSimple",
        "validacionEstado"
    })
    public static class ErrorCertificado {

        @XmlElement(required = true)
        protected String idCertificado;
        @XmlElement(name = "ValidacionSimple", required = true)
        protected ValidacionSimpleInfo validacionSimple;
        @XmlElement(name = "ValidacionEstado", required = true)
        protected ValidacionEstadoInfo validacionEstado;

        /**
         * Gets the value of the idCertificado property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdCertificado() {
            return idCertificado;
        }

        /**
         * Sets the value of the idCertificado property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdCertificado(String value) {
            this.idCertificado = value;
        }

        /**
         * Gets the value of the validacionSimple property.
         * 
         * @return
         *     possible object is
         *     {@link ValidacionSimpleInfo }
         *     
         */
        public ValidacionSimpleInfo getValidacionSimple() {
            return validacionSimple;
        }

        /**
         * Sets the value of the validacionSimple property.
         * 
         * @param value
         *     allowed object is
         *     {@link ValidacionSimpleInfo }
         *     
         */
        public void setValidacionSimple(ValidacionSimpleInfo value) {
            this.validacionSimple = value;
        }

        /**
         * Gets the value of the validacionEstado property.
         * 
         * @return
         *     possible object is
         *     {@link ValidacionEstadoInfo }
         *     
         */
        public ValidacionEstadoInfo getValidacionEstado() {
            return validacionEstado;
        }

        /**
         * Sets the value of the validacionEstado property.
         * 
         * @param value
         *     allowed object is
         *     {@link ValidacionEstadoInfo }
         *     
         */
        public void setValidacionEstado(ValidacionEstadoInfo value) {
            this.validacionEstado = value;
        }

    }

}
