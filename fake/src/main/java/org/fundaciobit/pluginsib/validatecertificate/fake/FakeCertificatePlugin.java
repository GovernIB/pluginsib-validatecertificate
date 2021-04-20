package org.fundaciobit.pluginsib.validatecertificate.fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.StringReader;
import java.security.cert.X509Certificate;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.validatecertificate.ICertificatePlugin;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

/**
 *
 * @author anadal
 *
 */
public class FakeCertificatePlugin implements ICertificatePlugin {

  protected final Logger log = Logger.getLogger(getClass());

  protected static final Map<String, String> mapKeyUsageExtended = new HashMap<String, String>();
  static {
    mapKeyUsageExtended.put("2.5.29.37.0", "Any extended key usage");
    mapKeyUsageExtended.put("1.3.6.1.5.5.7.3.1", "TLS Web server authentication");
    mapKeyUsageExtended.put("1.3.6.1.5.5.7.3.2", "TLS Web client authentication");
    mapKeyUsageExtended.put("1.3.6.1.5.5.7.3.4", "E-mail protection");
  }

  protected static final String[] KEY_USAGES = { "digitalSignature", // (0),
      "nonRepudiation", // (1),
      "keyEncipherment", // (2),
      "dataEncipherment", // (3),
      "keyAgreement", // (4),
      "keyCertSign", // (5),
      "cRLSign", // (6),
      "encipherOnly", // (7),
      "decipherOnly", // (8) }
  };

  @Override
  public ResultatValidacio getInfoCertificate(X509Certificate cert) throws Exception {

    ResultatValidacio result = new ResultatValidacio();

    String error = checkCertificate(cert);

    if (error == null) {
      result.setResultatValidacioCodi(0); // OK
      result.setResultatValidacioDescripcio("OK");
    } else {
      result.setResultatValidacioCodi(-1);
      result.setResultatValidacioDescripcio(error);
    }

    Properties propIssuer = new Properties();
    propIssuer.load(new StringReader(cert.getIssuerDN().getName().replaceAll(",", "\n")));
    // TODO

    // System.out.println("----- ISSUER -----------");
    // propIssuer.list(System.out);

    Properties propSubject = new Properties();
    propSubject.load(new StringReader(cert.getSubjectDN().getName().replaceAll(",", "\n")));
    // TODO

    // System.out.println("----- SUBJECT -----------");
    // propSubject.list(System.out);

    // private static
    // final String ISSUER_ALT_NAME_OID = "2.5.29.18";

    Map<String, String> map;
    {
      final String SUBJECT_ALT_NAME_OID = "2.5.29.17";
      map = CertificateUtils.getAlternativeNamesOfExtension(cert, SUBJECT_ALT_NAME_OID);

       for(String key: map.keySet()) {
       System.out.println(" +++]" + key +"[ ==> ]" + map.get(key)+ "[");
      
       }
    }

    InformacioCertificat info = new InformacioCertificat();
    info.setClassificacio(InformacioCertificat.CLASSIFICACIO_DESCONEGUDA);
    info.setDataNaixement(null);
    info.setEmail(propSubject.getProperty("EMAILADDRESS"));
    if (info.getEmail() == null) {
      info.setEmail(map.get("OID.2.16.724.1.3.5.3.2.9"));
    }

    info.setEmissorID(cert.getIssuerDN().getName().replace(", ", ","));
    info.setEmissorOrganitzacio(propIssuer.getProperty("O"));

    log.info("\n\n  NIF = " + CertificateUtils.getDNI(cert) + "\n\n");

    info.setNifResponsable(CertificateUtils.getDNI(cert));

    info.setNomResponsable(propSubject.getProperty("GIVENNAME"));

    info.setLlocDeFeina(CertificateUtils.getCarrec(cert));

    {
      String llinatges = propSubject.getProperty("SURNAME");
      if (llinatges == null) {
        llinatges = propSubject.getProperty("SN");
      }

      if (llinatges != null) {
        info.setLlinatgesResponsable(llinatges);
        String[] llinatgesSplit = llinatges.split(" ");
        if (llinatgesSplit.length == 2) {
          info.setPrimerLlinatgeResponsable(llinatgesSplit[0]);
          info.setSegonLlinatgeResponsable(llinatgesSplit[1]);
        } else {
          info.setPrimerLlinatgeResponsable(llinatges);
        }
      }
    }

    if (info.getNomResponsable() != null && info.getLlinatgesResponsable() != null) {
      info.setNomCompletResponsable(info.getNomResponsable() + " "
          + info.getLlinatgesResponsable());
    } else {
      //info.setNomCompletResponsable(propSubject.getProperty("CN"));
    }

    info.setNumeroSerie(cert.getSerialNumber());
    info.setPais(propSubject.getProperty("C"));

    
    
    
    info.setOrganitzacio(propSubject.getProperty("O"));
    info.setEntitatSubscriptoraNom(propSubject.getProperty("O"));
    info.setNumeroIdentificacionPersonal(map.get("OID.2.16.724.1.3.5.3.2.5"));


    // {
    // Map<String, String> map2;
    // final String OID = "2.5.29.16"; // "2.5.29.37"; //"2.5.29.15";
    // map2 = CertificateUtils.getAlternativeNamesOfExtension(cert, OID);
    //
    // for(String key: map2.keySet()) {
    // System.out.println(" +++]" + key +"[ ==> ]" + map2.get(key)+ "[");
    //
    // }
    // }

    {
      boolean[] keyUsage = cert.getKeyUsage();

      // System.out.println("BOOLEANS: " + Arrays.toString(keyUsage));

      StringBuffer str = new StringBuffer();

      for (int i = 0; i < keyUsage.length; i++) {
        if (keyUsage[i] == true) {
          if (str.length() == 0) {
            str.append(KEY_USAGES[i]);
          } else {
            str.append(" | ").append(KEY_USAGES[i]);
          }
        }
      }

      info.setUsCertificat(str.toString());

    }

    // Extensio de key usage
    {
      List<String> keyUsage = cert.getExtendedKeyUsage();

      // for (String ku : keyUsage) {
      // System.out.println("KEY_USAGES: " +ku.toString());
      // }

      int pos = 0;
      StringBuffer str = new StringBuffer();
      for (String ku : keyUsage) {
        String name = mapKeyUsageExtended.get(ku);

        String prefix = (str.length() == 0) ? "" : "\n";

        str.append(prefix).append("KeyPurposeId ").append(pos).append(":  ");
        pos++;
        if (name == null) {
          str.append(ku);
        } else {
          str.append(name);
        }

      }

      info.setUsCertificatExtensio(str.toString());

    }

    info.setValidDesDe(cert.getNotBefore());
    info.setValidFins(cert.getNotAfter());

    

    
    Map<String, String> subjectProperties = new HashMap<String, String>();
    {
      String dn = cert.getSubjectX500Principal().getName();
      LdapName ldapDN = new LdapName(dn);
      System.out.println("XYZ ZZZ ============ SUBJECT =========");
      for(Rdn rdn: ldapDN.getRdns()) {
        String type = rdn.getType();
        Object value =  rdn.getValue();
        if (value instanceof byte[]) {
          
          
          byte[] thearray = (byte[])value;
          
          StringBuffer str = new StringBuffer();
          for (int i = 0; i < thearray.length; i++) {
            if (thearray[i] < 32) {
              // ignorar
            } else {
              str.append((char)thearray[i]);
            }
          }
          
          subjectProperties.put(type, str.toString());
          System.out.println("SSS XYZ ZZZ  " + type + "[] -> " +  str.toString() );
          
          
        } else {
          
          System.out.println("SSS XYZ ZZZ  " + type + " -> " + value);
          
          if ("OU".equals(type)) {
            String ou = subjectProperties.get(type);
            if (ou == null) {
              subjectProperties.put(type, (String)value);
            } else {
              subjectProperties.put(type, " OrganizationUnit=" + ou + " OrganizationUnit="+ value);
            }

          }
          
          
          
        }
      }
    }
    
    info.setOiEuropeu(subjectProperties.get("2.5.4.97"));
    
    
    
    String ou = subjectProperties.get("OU");
    if (ou == null) {
      info.setUnitatOrganitzativa(map.get("OID.2.16.724.1.3.5.3.2.10"));
    } else {
      info.setUnitatOrganitzativa(ou);
    }
    
    

    
    
    
    
    
    
    
    Map<Integer, String> subjectAlternativeProperties = new HashMap<Integer, String>();
    {

      System.out.println("XYZ ZZZ ============ SUBJECT ALTERNATIVE NAMES =========");
    
      Collection<List<?>> subjectAltNames = cert.getSubjectAlternativeNames();
      
      if (subjectAltNames != null) {
         for (Object subjectAltName : subjectAltNames) {
          List<?> entry = (List<?>) subjectAltName;
          if (entry == null || entry.size() < 2) {
           continue;
          }
          Integer altNameType = (Integer) entry.get(0);
          if (altNameType == null) {
           continue;
          }
          //
          {
           String altName = (String) entry.get(1);
           if (altName != null) {
             System.out.println("XYZ ZZZ SAP   " + altNameType + " => " + altName);
             subjectAlternativeProperties.put(altNameType, altName);
             if (altNameType == 2) {
               // NOM DOMINI
               info.setNomDomini(altName);
               info.setNifResponsable(null);
               //info.setUnitatOrganitzativa(null);
             }
             
           }
          }
         }
       }

    }
    
    
    
    
    
    {
      String dn = cert.getIssuerX500Principal().getName();
      LdapName ldapDN = new LdapName(dn);
      System.out.println("XYZ ZZZ ============ ISSUER =========");
      for(Rdn rdn: ldapDN.getRdns()) {
        Object value =  rdn.getValue();
        if (value instanceof byte[]) {
          System.out.println("III XYZ ZZZ  " + rdn.getType() + "[] -> " + new String((byte[])value));
          
         
          
          
        } else {
          System.out.println("III XYZ ZZZ  " + rdn.getType() + " -> " + value);
        }
      }
    }
    
    
    Map<Integer, String> issuerAlternativeProperties = new HashMap<Integer, String>();
    {

      System.out.println("XYZ ZZZ ============ ISSUER ALTERNATIVE NAMES =========");
    
      Collection<List<?>> issuerAltNames = cert.getIssuerAlternativeNames();
      
      if (issuerAltNames != null) {
         for (Object subjectAltName : issuerAltNames) {
          List<?> entry = (List<?>) subjectAltName;
          if (entry == null || entry.size() < 2) {
           continue;
          }
          Integer altNameType = (Integer) entry.get(0);
          if (altNameType == null) {
           continue;
          }
          //
          {
           String altName = (String) entry.get(1);
           if (altName != null) {
             System.out.println("XYZ ZZZ SAP   " + altNameType + " => " + altName);
             issuerAlternativeProperties.put(altNameType, altName);
             if (altNameType == 2) {
               // NOM DOMINI
               info.setNomDomini(altName);
               info.setNifResponsable(null);
               info.setUnitatOrganitzativa(null);
             }
             
           }
          }
         }
       }

    }
    
    
    
    // ----------- FALTA
    //    info.setTipusCertificat(null);
    //    info.setCertificatQualificat(null);
    //    info.setClassificacio(-1);
    //    info.setClassificacioEidas(null);
    //    info.setPoliticaID(null);
    //    info.setPoliticaVersio(null);
    //    info.setTipusCertificat(null);
    //    info.setUnitatOrganitzativaNifCif(null);
    //    info.setPolitica(CertificateUtils.getCertificatePolicyId(cert));
    //    info.setPoliticaID(null);
    //    info.setPoliticaVersio(null);
    //    info.setRaoSocial(null);
    //    info.setSubject(cert.getSubjectDN().getName().replace(", ", ",").replace("SURNAME=", "SN=").replace("OID.2.5.4.13=", "description"));
    //    info.setEntitatSubscriptoraNif(info.getNifResponsable());
    //    info.setEntitatSubscriptoraNif(map.get("OID.2.16.724.1.3.5.3.2.3"));
    //    info.setCreatAmbUnDispositiuSegur(creatAmbUnDispositiuSegur);
    //    info.setDenominacioSistemaComponent(null);
    
    
    
    //System.out.println(" XYZ ZZZZ TYPE =>  " + cert.getType());

    
    
//     {
//     // private static
//     final String CERTIFICATE_POLICY_OID = "2.5.4.97"; //"2.5.29.32";
//    
//     Map<String, String> map2;
//     //final String OID = "2.5.29.16"; // "2.5.29.37"; //"2.5.29.15";
//     map2 = CertificateUtils.getAlternativeNamesOfExtension(cert, CERTIFICATE_POLICY_OID);
//    
//     if (map2.isEmpty()) {
//     System.out.println(" MAP esta buit");
//     }
//    
//     for(String key: map2.keySet()) {
//     System.out.println(" +++]" + key +"[ ==> ]" + map2.get(key)+ "[");
//    
//     }
//    
//     }

    result.setInformacioCertificat(info);

    return result;

  }

  @Override
  public String checkCertificate(X509Certificate cert) throws Exception {

    try {

      long now = System.currentTimeMillis();

      java.util.Date from = cert.getNotBefore();
      System.out.println("From: " + from);
      if (now < from.getTime()) {
        return "La data d´inici del certificat es posterior a la data actual";
      }

      java.util.Date to = cert.getNotAfter();
      System.out.println("To: " + to);
      if (now > to.getTime()) {
        return "El certificat ha caducat";
      }

      return null;

    } catch (Exception e) {

      log.error("Error no controlat cridant a @firma " + e.getMessage(), e);

      return e.getMessage();
    }

  }

}
