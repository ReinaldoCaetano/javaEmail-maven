package br.com.proj.enviarEmail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail {

	
	private String userName = "reinaldovcaetano@gmail.com";
	private String passWord = "tmhbtsiiqxmabaok";
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	

	
	
	public EnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

//

	public void enviarEmail(boolean envioHtml) throws Exception {
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.thust", "*");
		properties.put("mail.smtp.auth", "true"); 
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication(userName, passWord);
		}
		});
		
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName , nomeRemetente)); //quem esta enviando
		message.setRecipients(Message.RecipientType.TO, toUser); // email destino
		message.setSubject(assuntoEmail); //assunto do email
		
		if(envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
			message.setText(textoEmail); // texto do email
		}
		Transport.send(message);
	}
}
