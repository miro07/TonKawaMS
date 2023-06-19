package auth.services.mailservices;


import auth.services.mailservices.MailSenderImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailSenderImpl mailSender;

    @Test
    public void sendEmail() throws MessagingException, IOException {
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Text";
        MimeMessage mimeMessage=Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailSender.sendEmail(to, subject, text);

        verify(javaMailSender).send(mimeMessage);
    }
    @Test
    public void sendEmailWithAttachment() throws MessagingException {
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Text";
        File file = mock(File.class);
        String fileName = "test file name";

        MimeMessage mimeMessage=Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailSender.sendEmailWithAttachment(to, subject, text, file, fileName);

        verify(javaMailSender).send(mimeMessage);
    }
    @Test
    public void sendEmailWithInlineDataSource() throws MessagingException, IOException {
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Text";
        ByteArrayDataSource dataSource = mock(ByteArrayDataSource.class);
        Boolean isHtml = true;
        String dataSourceName = "test datasource name";

        MimeMessage mimeMessage=Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailSender.sendEmailWithInlineDataSource(to, subject, text, isHtml, dataSource, dataSourceName);

        verify(javaMailSender).send(mimeMessage);
    }

}
