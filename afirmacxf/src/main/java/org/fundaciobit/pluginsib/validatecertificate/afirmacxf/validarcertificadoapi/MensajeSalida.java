
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
 *         &amp;lt;element name="respuesta"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;choice&amp;gt;
 *                   &amp;lt;element name="ResultadoProcesamiento"&amp;gt;
 *                     &amp;lt;complexType&amp;gt;
 *                       &amp;lt;complexContent&amp;gt;
 *                         &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                           &amp;lt;sequence&amp;gt;
 *                             &amp;lt;element name="InfoCertificado" type="{http://afirmaws/ws/validacion}InfoCertificadoInfo" minOccurs="0"/&amp;gt;
 *                             &amp;lt;element name="ResultadoValidacion" type="{http://afirmaws/ws/validacion}ResultadoValidacionInfo" minOccurs="0"/&amp;gt;
 *                           &amp;lt;/sequence&amp;gt;
 *                         &amp;lt;/restriction&amp;gt;
 *                       &amp;lt;/complexContent&amp;gt;
 *                     &amp;lt;/complexType&amp;gt;
 *                   &amp;lt;/element&amp;gt;
 *                   &amp;lt;element name="Excepcion"&amp;gt;
 *                     &amp;lt;complexType&amp;gt;
 *                       &amp;lt;complexContent&amp;gt;
 *                         &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                           &amp;lt;sequence&amp;gt;
 *                             &amp;lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *                             &amp;lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *                             &amp;lt;element name="excepcionAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *                           &amp;lt;/sequence&amp;gt;
 *                         &amp;lt;/restriction&amp;gt;
 *                       &amp;lt;/complexContent&amp;gt;
 *                     &amp;lt;/complexType&amp;gt;
 *                   &amp;lt;/element&amp;gt;
 *                 &amp;lt;/choice&amp;gt;
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
    "respuesta"
})
@XmlRootElement(name = "mensajeSalida")
public class MensajeSalida {

    @XmlElement(required = true)
    protected String peticion;
    @XmlElement(required = true)
    protected String versionMsg;
    @XmlElement(required = true)
    protected MensajeSalida.Respuesta respuesta;

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
     * Gets the value of the respuesta property.
     * 
     * @return
     *     possible object is
     *     {@link MensajeSalida.Respuesta }
     *     
     */
    public MensajeSalida.Respuesta getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link MensajeSalida.Respuesta }
     *     
     */
    public void setRespuesta(MensajeSalida.Respuesta value) {
        this.respuesta = value;
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
     *       &amp;lt;choice&amp;gt;
     *         &amp;lt;element name="ResultadoProcesamiento"&amp;gt;
     *           &amp;lt;complexType&amp;gt;
     *             &amp;lt;complexContent&amp;gt;
     *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     *                 &amp;lt;sequence&amp;gt;
     *                   &amp;lt;element name="InfoCertificado" type="{http://afirmaws/ws/validacion}InfoCertificadoInfo" minOccurs="0"/&amp;gt;
     *                   &amp;lt;element name="ResultadoValidacion" type="{http://afirmaws/ws/validacion}ResultadoValidacionInfo" minOccurs="0"/&amp;gt;
     *                 &amp;lt;/sequence&amp;gt;
     *               &amp;lt;/restriction&amp;gt;
     *             &amp;lt;/complexContent&amp;gt;
     *           &amp;lt;/complexType&amp;gt;
     *         &amp;lt;/element&amp;gt;
     *         &amp;lt;element name="Excepcion"&amp;gt;
     *           &amp;lt;complexType&amp;gt;
     *             &amp;lt;complexContent&amp;gt;
     *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     *                 &amp;lt;sequence&amp;gt;
     *                   &amp;lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
     *                   &amp;lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
     *                   &amp;lt;element name="excepcionAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
     *                 &amp;lt;/sequence&amp;gt;
     *               &amp;lt;/restriction&amp;gt;
     *             &amp;lt;/complexContent&amp;gt;
     *           &amp;lt;/complexType&amp;gt;
     *         &amp;lt;/element&amp;gt;
     *       &amp;lt;/choice&amp;gt;
     *     &amp;lt;/restriction&amp;gt;
     *   &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "resultadoProcesamiento",
        "excepcion"
    })
    public static class Respuesta {

        @XmlElement(name = "ResultadoProcesamiento")
        protected MensajeSalida.Respuesta.ResultadoProcesamiento resultadoProcesamiento;
        @XmlElement(name = "Excepcion")
        protected MensajeSalida.Respuesta.Excepcion excepcion;

        /**
         * Gets the value of the resultadoProcesamiento property.
         * 
         * @return
         *     possible object is
         *     {@link MensajeSalida.Respuesta.ResultadoProcesamiento }
         *     
         */
        public MensajeSalida.Respuesta.ResultadoProcesamiento getResultadoProcesamiento() {
            return resultadoProcesamiento;
        }

        /**
         * Sets the value of the resultadoProcesamiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link MensajeSalida.Respuesta.ResultadoProcesamiento }
         *     
         */
        public void setResultadoProcesamiento(MensajeSalida.Respuesta.ResultadoProcesamiento value) {
            this.resultadoProcesamiento = value;
        }

        /**
         * Gets the value of the excepcion property.
         * 
         * @return
         *     possible object is
         *     {@link MensajeSalida.Respuesta.Excepcion }
         *     
         */
        public MensajeSalida.Respuesta.Excepcion getExcepcion() {
            return excepcion;
        }

        /**
         * Sets the value of the excepcion property.
         * 
         * @param value
         *     allowed object is
         *     {@link MensajeSalida.Respuesta.Excepcion }
         *     
         */
        public void setExcepcion(MensajeSalida.Respuesta.Excepcion value) {
            this.excepcion = value;
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
         *         &amp;lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
         *         &amp;lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
         *         &amp;lt;element name="excepcionAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
            "codigoError",
            "descripcion",
            "excepcionAsociada"
        })
        public static class Excepcion {

            @XmlElement(required = true)
            protected String codigoError;
            @XmlElement(required = true)
            protected String descripcion;
            protected String excepcionAsociada;

            /**
             * Gets the value of the codigoError property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodigoError() {
                return codigoError;
            }

            /**
             * Sets the value of the codigoError property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodigoError(String value) {
                this.codigoError = value;
            }

            /**
             * Gets the value of the descripcion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescripcion() {
                return descripcion;
            }

            /**
             * Sets the value of the descripcion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescripcion(String value) {
                this.descripcion = value;
            }

            /**
             * Gets the value of the excepcionAsociada property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExcepcionAsociada() {
                return excepcionAsociada;
            }

            /**
             * Sets the value of the excepcionAsociada property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExcepcionAsociada(String value) {
                this.excepcionAsociada = value;
            }

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
         *         &amp;lt;element name="InfoCertificado" type="{http://afirmaws/ws/validacion}InfoCertificadoInfo" minOccurs="0"/&amp;gt;
         *         &amp;lt;element name="ResultadoValidacion" type="{http://afirmaws/ws/validacion}ResultadoValidacionInfo" minOccurs="0"/&amp;gt;
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
            "infoCertificado",
            "resultadoValidacion"
        })
        public static class ResultadoProcesamiento {

            @XmlElement(name = "InfoCertificado")
            protected InfoCertificadoInfo infoCertificado;
            @XmlElement(name = "ResultadoValidacion")
            protected ResultadoValidacionInfo resultadoValidacion;

            /**
             * Gets the value of the infoCertificado property.
             * 
             * @return
             *     possible object is
             *     {@link InfoCertificadoInfo }
             *     
             */
            public InfoCertificadoInfo getInfoCertificado() {
                return infoCertificado;
            }

            /**
             * Sets the value of the infoCertificado property.
             * 
             * @param value
             *     allowed object is
             *     {@link InfoCertificadoInfo }
             *     
             */
            public void setInfoCertificado(InfoCertificadoInfo value) {
                this.infoCertificado = value;
            }

            /**
             * Gets the value of the resultadoValidacion property.
             * 
             * @return
             *     possible object is
             *     {@link ResultadoValidacionInfo }
             *     
             */
            public ResultadoValidacionInfo getResultadoValidacion() {
                return resultadoValidacion;
            }

            /**
             * Sets the value of the resultadoValidacion property.
             * 
             * @param value
             *     allowed object is
             *     {@link ResultadoValidacionInfo }
             *     
             */
            public void setResultadoValidacion(ResultadoValidacionInfo value) {
                this.resultadoValidacion = value;
            }

        }

    }

}
