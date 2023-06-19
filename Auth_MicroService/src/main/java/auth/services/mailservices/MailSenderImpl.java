package auth.services.mailservices;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class MailSenderImpl implements MailSender {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String text) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        javaMailSender.send(message);
    }
    @Override
    public void sendEmailWithAttachment(String to, String subject, String text, File attachment, String attachmentName) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment(attachmentName, attachment);

        javaMailSender.send(message);
    }

    public void sendEmailWithInlineDataSource(String to, String subject, String text, Boolean isHtmlText, DataSource dataSource, String dataSourceName) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text,isHtmlText);

        helper.addInline(dataSourceName, dataSource);
        javaMailSender.send(message);
    }
}
