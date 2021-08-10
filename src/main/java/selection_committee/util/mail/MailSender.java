package selection_committee.util.mail;

import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
public class MailSender {
    private static final String EMAIL = "sellection.committee@gmail.com";
    private static final String PASSWORD = "qwerty123qwerty";
    private static final Properties props = new Properties();

    static {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", EMAIL);
        props.put("mail.password", PASSWORD);
    }

    private MailSender() {
    }

    public static void sendMail(String recipientAddress, String subject, String text) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            log.info("Email send");
        } catch (MessagingException ex) {
            log.error("Error while sending  email: " + ex.getMessage());
        }
    }
}
