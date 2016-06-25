package hackathon.bot.application.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailService {

	private static final String username = "alok.kumar@vuclip.com";
	private static final String password = "@mit27MAY";

	public static String sendOutlookMail(String to, String msg, String subject) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);

			System.out.println("Done");
			return "Mail sent successfully !!!";
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Couldn't Sent Mail.";
	}

	public static void main(String[] args) {
		new SendMailService().sendOutlookMail("alok.kumar@vuclip.com", "Hi, Mr. Alok Kumar", "Second Testing");

	}

}
