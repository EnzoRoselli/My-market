package tesis.subscription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static javax.mail.Session.getInstance;

@Slf4j
@Service
public class BulkEmailSender {
    @Autowired
    private SubsRepository repo;

    public void send() {
        List<String> emails = new LinkedList<>(repo.takeMailInformation());

        String subject = "LAS OFERTAS QUE TENEMOS!";

        String message = "MIRA LAS INCREIBLES OFERTAS PARA TUS PRODUCTOS SELECCIONADOS: www.google.com";
        log.info("EMPIEZA A MANDAR LOS MAILS");
        sendBulkEmail(subject, emails, message);
    }

    private static void sendBulkEmail(final String subject, final List<String> emailToAddresses,
                                      final String emailBodyText) {

        final String username = "ofertasargentinaya@gmail.com";

        final String password = "elmarketmuypiola";
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String emails = null;

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setSubject(subject);

            String content = "<html>\n<body>\n";
            content += emailBodyText + "\n";
            content += "\n";
            content += "</body>\n</html>";
            message.setContent(content, "text/html");

            StringBuilder sb = new StringBuilder();

            int i = 0;
            for (String email : emailToAddresses) {
                sb.append(email);
                i++;
                if (emailToAddresses.size() > i) {
                    sb.append(", ");
                }
            }

            emails = sb.toString();

            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(sb.toString()));

            Transport.send(message);

            log.info("SE ENVIARON CON EXITO");
        } catch (MessagingException e) {
            System.out.println("Email sending failed to " + emails);
            System.out.println(e);
            log.error("-ERROR-FALLARON LOS MAILS");
        }
    }

}