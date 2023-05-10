package br.com.proj.enviarEmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


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
	
	public void enviarEmailAnexo(boolean envioHtml) throws Exception {
			
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
			
			
			MimeBodyPart corpoEmail = new MimeBodyPart();
			
			if(envioHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
			}else {
				corpoEmail.setText(textoEmail); // texto do email
			}
			
			//simulando envio de varios anexos
			List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
			arquivos.add(simuladorPdf());
			arquivos.add(simuladorPdf());
			arquivos.add(simuladorPdf());
			arquivos.add(simuladorPdf());
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			
			for (FileInputStream fileInputStream : arquivos) {
				
			
			
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream,"aplication/pdf")));
			anexoEmail.setFileName("fileanexo.pdf");
			
			multipart.addBodyPart(anexoEmail);	
			
			}
			message.setContent(multipart);
		
			
			Transport.send(message);
		}
	
	private FileInputStream simuladorPdf() throws Exception{
		Document documento = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(documento, new FileOutputStream(file));
		documento.open();
		documento.add(new Paragraph("Conteudo pdf para teste"));
		documento.close();
		
		return new FileInputStream(file);
	}
}
