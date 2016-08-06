package pe.upc.edu.alquiler.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class EnvioMail {

	
	public boolean envioMail(String razonSocial, String correo, String estado, String repLegal, String ruc, Date fechaMod)
	    {
		
		
		/*String razonSocial = evaluacion.getSolicitud().getLocatario().getRazonSocial();
		String correo = evaluacion.getSolicitud().getLocatario().getCorreo();
		String estado = evaluacion.getEstado();
		String repLegal = evaluacion.getSolicitud().getLocatario().getRepresentante();
		String ruc = evaluacion.getSolicitud().getLocatario().getRuc();*/
		SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		
		String fechaAct = format.format(fechaMod);
		
		 System.out.println("ENVIANDO CORREO A : " +razonSocial + "al CORREO " + correo);
		
	    	boolean x = true;
	    	 try {
	             MailcapCommandMap mailcap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	             mailcap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
	             mailcap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
	             mailcap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
	             mailcap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
	             mailcap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");

	             CommandMap.setDefaultCommandMap(mailcap);

	             
	 			// Create the multi-part
	 		    Multipart multipart = new MimeMultipart();

	 			BodyPart messageBodyPart = new MimeBodyPart();

	 		    // Fill the message
	 		    messageBodyPart.setText("");

	 		    // Add the first part
	 		    multipart.addBodyPart(messageBodyPart);

	 			//Part two is attachment
	 		    messageBodyPart = new MimeBodyPart();
	 		 //   messageBodyPart.setText("Acceder a la siguiente direcci�n:  http://190.222.248.250:8080/SignedPro/ObtenerDocumento?pdf="+nomDoc+".pdf");

	 		    // Add the second part
	 		    multipart.addBodyPart(messageBodyPart);
	 		    
	 		    MimeBodyPart m = new MimeBodyPart();
	 		    m.setContent(multipart);
	 		 
	             Properties props = System.getProperties();
	             props.put("mail.smtp.host", "smtp.gmail.com");
	             props.setProperty("mail.smtp.port", "587");
	             props.setProperty("mail.smtp.auth", "true");
	             props.setProperty("mail.smtp.starttls.enable", "true");
	             props.setProperty ("mail.transport.protocol", "smtp");
	             Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	   			  protected PasswordAuthentication getPasswordAuthentication() {
	   			    return new PasswordAuthentication("arquitectura.sw.upc@gmail.com", "upcupc123");
	   			  }
	   			});
	     	//	session.setDebug(true);

	             MimeMessage body = new MimeMessage(session);
	             
	             
	 			SimpleDateFormat formateador = new SimpleDateFormat("'San Borja' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
				Date fechaDate = new Date();
				String fecha = formateador.format(fechaDate);
				
				String mensaje = "";
				
				if(estado.equals("Aprobada"))
					mensaje = "La solicitud de alquiler fue aprobada, se invita al representante legal, "+ repLegal +
					", visitar el área legal para firmar el contrato correspondiente.";
				else if(estado.equals("Rechazada"))
					mensaje = "La solicitud de alquiler fue rechazada, se le invita a nuestras oficinas para conocer el detalle del estado de la solicitud.";

	             body.setFrom(new InternetAddress("arquitectura.sw.upc@gmail.com"));
	             body.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
	             body.setSubject("Envio Automatico - AVISO DE ESTADO DE EVALUACION DE SOLICITUD DE ALQUILER DE ESPACIO COMERCIAL ::  PLAZA VEA");
	             body.setText("Envío Automatico - Aviso de Estado de Evaluación de Solicitud de Alquiler de Espacio Comercial"+ " " +"\n" + "\n" + 
	            		 fecha + "\n" + 
	            		 "\n" + 
	             		"Señores: " + " " +"\n" + 
	            		razonSocial + "( RUC: " + ruc + ")" + "\n" + "\n" + 
	             		"Estimado locatario: " + "\n" + "\n" + 
	             		"El presente correo es para comunicarle que el día " + fechaAct + 
	             		" finalizó la evaluación de la solicitud de alquiler que presento en nuestras oficinas." + "\n" +
	             		mensaje+
	             		"" +"\n" + "\n" + 
	             		"Sin otro particular reciba nuestros saludos," +"\n" + "\n" + 
	             		"Atentamente,"+"\n" + "\n" + 
	             		"Plaza Vea");
	            

	             Transport t = session.getTransport("smtp");
	       
	             t.connect("arquitectura.sw.upc@gmail.com", "upcupc123");

	             t.sendMessage(body,body.getAllRecipients());
	             
	             t.close();
	             
	         }

	    	 
	         catch (Exception ex)
	         {
	             ex.printStackTrace(System.err);
	             x = false;
	             System.out.println("NO SE ENVIO EL CORREO A : " + correo);
	         }
		 
	            
	          return x;
	        }
	
	
}
