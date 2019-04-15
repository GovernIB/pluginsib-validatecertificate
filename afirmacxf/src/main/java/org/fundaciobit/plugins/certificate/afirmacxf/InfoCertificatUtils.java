package org.fundaciobit.plugins.certificate.afirmacxf;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.certificate.InformacioCertificat;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class InfoCertificatUtils {
  
  private static final Logger log = Logger.getLogger(InfoCertificatUtils.class);
  
  private static final SimpleDateFormat SDF = new SimpleDateFormat(
      "yyyy-MM-dd EEE HH:mm:ss Z", new Locale("es"));

  
  public static InformacioCertificat processInfoCertificate(Map<String, Object> camps) {
    InformacioCertificat dades = new InformacioCertificat();

    for (String key : camps.keySet()) {

      //String key = camp.getIdCampo();
      String value = (String)camps.get(key);
      if ("tipoCertificado".equalsIgnoreCase(key)) {
        dades.setTipusCertificat(value);
        continue;
      }
      if ("subject".equalsIgnoreCase(key)) {
        dades.setSubject(value);
        continue;
      }
      if ("NombreApellidosResponsable".equalsIgnoreCase(key)) {
        dades.setNomCompletResponsable(value);
        continue;
      }
      if ("nombreResponsable".equalsIgnoreCase(key)) {
        dades.setNomResponsable(value);
        continue;
      }
      if ("primerApellidoResponsable".equalsIgnoreCase(key)) {
        dades.setPrimerLlinatgeResponsable(value);
        continue;
      }
      if ("segundoApellidoResponsable".equalsIgnoreCase(key)) {
        dades.setSegonLlinatgeResponsable(value);
        continue;
      }
      if ("NIFResponsable".equalsIgnoreCase(key)) {
        dades.setNifResponsable(value);
        continue;
      }
      if ("idEmisor".equalsIgnoreCase(key)) {
        dades.setEmissorID(value);
        continue;
      }
      if ("NIF-CIF".equalsIgnoreCase(key)) {
        dades.setUnitatOrganitzativaNifCif(value);
        continue;
      }
      if ("email".equalsIgnoreCase(key)) {
        dades.setEmail(value);
        continue;
      }
      if ("fechaNacimiento".equalsIgnoreCase(key)) {
        dades.setDataNaixement(value);
        continue;
      }
      if ("razonSocial".equalsIgnoreCase(key)) {
        dades.setRaoSocial(value);
        continue;
      }
      if ("clasificacion".equalsIgnoreCase(key)) {
        dades.setClassificacio(Integer.parseInt(value));
        continue;
      }
      if ("numeroSerie".equalsIgnoreCase(key)) {
        dades.setNumeroSerie(new BigInteger(value));
        continue;
      }
      if ("usoCertificado".equalsIgnoreCase(key)) {
        dades.setUsCertificat(value);
        continue;
      }
      if ("extensionUsoCertificado".equalsIgnoreCase(key)) {
        dades.setUsCertificatExtensio(value);
        continue;
      }
      if ("validoHasta".equalsIgnoreCase(key)) {
        try {
          dades.setValidFins(SDF.parse(value));
        } catch (ParseException e) {
          log.error("Error desconegut parsejant la data de final " + value, e);
        }
        continue;
      }
      if ("validoDesde".equalsIgnoreCase(key)) {
        try {
          dades.setValidDesDe(SDF.parse(value));
        } catch (ParseException e) {
          log.error("Error desconegut parsejant la data d'inici " + value, e);
        }
        continue;
      }

      if ("politica".equalsIgnoreCase(key)) {
        dades.setPolitica(value);
        continue;
      }

      if ("versionPolitica".equalsIgnoreCase(key)) {
        dades.setPoliticaVersio(value);
        continue;
      }

      if ("idPolitica".equalsIgnoreCase(key)) {
        dades.setPoliticaID(value);
        continue;
      }

      if ("OrganizacionEmisora".equalsIgnoreCase(key)) {
        dades.setEmissorOrganitzacio(value);
        continue;
      }

      if ("pais".equalsIgnoreCase(key)) {
        dades.setPais(value);
        continue;
      }

      if ("unidadOrganizativa".equalsIgnoreCase(key)) {
        dades.setUnitatOrganitzativa(value);
        continue;
      }
      
      
      
      if ("ApellidosResponsable".equalsIgnoreCase(key)) {
        dades.setLlinatgesResponsable(value);
        continue;
      }
      
      
      if ("ID_europeo".equalsIgnoreCase(key)) {
        dades.setIdEuropeu(value);
        continue;
      }
      
      if ("OI_Europeo".equalsIgnoreCase(key)) {
        dades.setOiEuropeu(value);
        continue;
      }
      
      
      if ("entidadSuscriptora".equalsIgnoreCase(key)) {
        dades.setEntitatSubscriptoraNom(value);
        continue;
      }
      
      
      if ("NIFEntidadSuscriptora".equalsIgnoreCase(key)) {
        dades.setEntitatSubscriptoraNif(value);
        continue;
      }
      
      if ("certClassification".equalsIgnoreCase(key)) {
        dades.setClassificacioEidas(value);
        continue;
      }
      
      if ("certQualified".equalsIgnoreCase(key)) {
        if ("YES".equals(value)) {
          dades.setCertificatQualificat(true);
        } else if ("NO".equals(value)) {
          dades.setCertificatQualificat(false);
        } else {
          dades.setCertificatQualificat(null);
        }
        continue;
      }
      
      if ("puesto".equalsIgnoreCase(key)) {
        dades.setLlocDeFeina(value);
        continue;
      }
      
      
      if ("cargo".equalsIgnoreCase(key)) {
        dades.setCarrec(value);
        continue;
      }
      
      if ("qscd".equalsIgnoreCase(key)) {
        if ("YES".equals(value)) {
          dades.setCreatAmbUnDispositiuSegur(true);
        } else if ("NO".equals(value)) {
          dades.setCreatAmbUnDispositiuSegur(false);
        } else {
          dades.setCreatAmbUnDispositiuSegur(null);
        }
        continue;
      }

      if ("organizacion".equalsIgnoreCase(key)) {
        dades.setOrganitzacio(value);
        continue;
      }
      
      if ("NombreDominioIP".equalsIgnoreCase(key)) {
        dades.setNomDomini(value);
        continue;
      }
      
      if ("DenominacionSistemaComponente".equals(key)) {
        dades.setDenominacioSistemaComponent(value);
        continue;
      }
      
      if ("seudonimo".equals(key)) {
        dades.setPseudonim(value);
        continue;
      }
      
      if ("Documento representación".equals(key)) {
        dades.setDocumentRepresentacio(value);
        continue;
      }
      
      
      if ("numeroIdentificacionPersonal".equalsIgnoreCase(key)) {
        dades.setNumeroIdentificacionPersonal(value);
        continue;
      }

      if ("QcCompliance".equalsIgnoreCase(key)) {
        dades.setNumeroIdentificacionPersonal(value);
        continue;
      }

      if ("QcSSCD".equalsIgnoreCase(key)) {
        dades.setNumeroIdentificacionPersonal(value);
        continue;
      }

      if ("IDlogOn".equalsIgnoreCase(key)) {
        dades.setNumeroIdentificacionPersonal(value);
        continue;
      }

      dades.getAltresValors().put(key, value);

    }
    return dades;
  }
  
}
