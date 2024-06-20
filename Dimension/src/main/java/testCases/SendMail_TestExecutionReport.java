package testCases;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import commonFunctions.CommonFunctions;

public class SendMail_TestExecutionReport extends CommonFunctions {
	
	public void sendMail() {
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.socketFactory.port","587");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication("lthiyagaraj@speedstep.de", "Thiyag@1982");

					}

				});
		try {
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("lthiyagaraj@speedstep.de"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("lthiyagaraj@speedstep.de"));
			
			message.setSubject("Dimension Test Execution");
			
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Dimension Automation Test Execution Report attached");
			
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String filename = "ExtentReport.html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart1);
			
			message.setContent(multipart);
			
			
			Transport.send(message);
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
				
	}

}
