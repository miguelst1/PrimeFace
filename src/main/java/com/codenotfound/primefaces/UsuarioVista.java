package com.codenotfound.primefaces;

import java.io.Serializable;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.event.FlowEvent;
import com.codenotfound.primefaces.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioVista implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario user = new Usuario();

	private boolean skip;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public void save() {
		enviarConGMail("ajcalle@gmail.com", "Envio de información", user.toString());
		FacesMessage msg = new FacesMessage("Información enviada correctamente ", " " + user.getFirstname());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "nomcuenta"; // Para la dirección nomcuenta@gmail.com
		String clave = "miclave"; // mi clave de cuenta gmail.

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de
														// Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "miClaveDeGMail"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante
												// usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de
														// manera segura al
														// servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario); // Se
																			// podrían
																			// añadir
																			// varios
																			// de
																			// la
																			// misma
																			// manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

}