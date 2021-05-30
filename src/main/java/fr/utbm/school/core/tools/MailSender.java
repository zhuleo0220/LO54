package fr.utbm.school.core.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * @author Neil Farmer / Ruiquing Zhu
 */
public class MailSender {
    // Sender's email
    String sender = "LO54.projetutbm@gmail.com";

    // sending email from through gmails smtp
    String host = "smtp.gmail.com";

    // Get system properties
    Properties properties = System.getProperties();

    /**
     * Constructor
     */
    public MailSender(){
        // Setup mail server
        this.properties.put("mail.smtp.host", this.host);
        this.properties.put("mail.smtp.socketFactory.port", "465");
        this.properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.port", "465");
    }


    /**
     * Method to send a mail
     *
     * @param receiver email of the receiver
     * @param header header of the email
     * @param body body of the email
     * @return A boolean to know if the mail have been send
     */
    public boolean sendMail(String receiver, String header, String body){
        // Get the Session object
        // and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("LO54.projetutbm@gmail.com", "9Mfj@Mqaf+qd#+9^");
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(this.sender));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            // Set Subject: header field
            mimeMessage.setSubject(header);

            // Now set the actual message
            mimeMessage.setText(body);

            // Send message
            Transport.send(mimeMessage);

        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }

        return true;
    }
}
