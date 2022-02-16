package es.uma.informatica.sii.tarea3.negocio;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;



public class EnviarCorreo {
	
	private final String username = "accionsocialmed@gmail.com";
	private final String password = "siipassword";


	public EnviarCorreo() {
	
	}

	public void enviarValidacion(Usuario u, String url) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Define message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setSubject("Verificacion de cuenta");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(u.getEmail()));
			message.setText("Hola "+u.getNombre()+",\n\nBienvenido a Accion Social Med! Pulsa en el siguiente enlace para verificar tu cuenta: "+url+"\n\nNo responda a este mensaje. Gracias!\nAccion Social Med");
			// Envia el mensaje
			Transport.send(message);
		} catch (Exception e) {
			
		}
	}
	
	public void enviarNotificacion(Notificacion n) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Define message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setSubject(n.getTitulo());
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(n.getUsuario().getEmail()));
			message.setText("Hola "+n.getUsuario().getNombre()+",\n\n"+n.getDescripcion()+"\n\nNo responda a este mensaje. ¡Gracias!\nAccion Social Med");
			// Envia el mensaje
			Transport.send(message);
		} catch (Exception e) {
			
		}
	}
}
