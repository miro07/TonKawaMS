package auth.services.mailservices;

import auth.services.mailservices.MailSender;
import auth.services.mailservices.MailService;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {
    @Mock
    MailSender mailSender;
    @InjectMocks
    private MailService mailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void sendSignupEmail() throws MessagingException, IOException {
        String to = "recipient@example.com";
        String subject = "Test Subject";
        BufferedImage barcode = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        String username = "testusername";
        mailService.sendSignupEmail(to, subject, barcode, username);
        verify(mailSender).sendEmailWithInlineDataSource(
                eq(to),
                eq(subject),
                anyString(),
                eq(true),
                any(DataSource.class),
                eq("barcodeId")
        );
    }
}
