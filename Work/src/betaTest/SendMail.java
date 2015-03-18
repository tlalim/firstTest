package betaTest;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class SendMail {


	public void sendMail(String msg, File statText,String timeStamp)
			throws IOException {
		  final String username = "IT@tlalimgroup.com";
		    final String password = "123IT456";

			String fileName = System.getProperty("user.name");

		    Properties props = new Properties();
		 
		    props.put("mail.smtp.host", "192.168.211.13");
		    props.put("mail.smtp.port", "25");
		    String fileAttachment = "U:\\HelpDesk\\"+ fileName+timeStamp +".txt";
		    Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		        }
		      });

		    try {

		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(fileName+"@helpdesk.com"));
		        message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse("alex@tlalimgroup.com"));
		        message.setSubject("HELPDESK");
		      	  
	            MimeBodyPart messageBodyPart = 
	              new MimeBodyPart();
	        //fill message
	            messageBodyPart.setText("Test mail one");
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);
	         // Part two is attachment
	            messageBodyPart = new MimeBodyPart();
	            javax.activation.DataSource source = 
	              new FileDataSource(fileAttachment);
	            messageBodyPart.setDataHandler(
	              new DataHandler(source));
	            messageBodyPart.setFileName(fileName+" "+timeStamp+".txt");
	            multipart.addBodyPart(messageBodyPart);
	             message.setContent(multipart);
	         	            Transport.send(message);
		  
		    } catch (MessagingException e) {
		        throw new RuntimeException(e);
		    }
		}
	public void sendMailWoutfile(String msg,String timeStamp)
			throws IOException {
		  final String username = "IT@tlalimgroup.com";
		    final String password = "123IT456";

			String fileName = System.getProperty("user.name");

		    Properties props = new Properties();
		 
		    props.put("mail.smtp.host", "192.168.211.13");
		    props.put("mail.smtp.port", "25");
		  
		    Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		        }
		      });

		    try {

		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(fileName+"@helpdesk.com"));
		        message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse("alex@tlalimgroup.com"));
		        message.setSubject("HELPDESK");
		       
	            message.setText(msg);
	         Transport.send(message);
		  
		    } catch (MessagingException e) {
		        throw new RuntimeException(e);
		    }
		}
		}