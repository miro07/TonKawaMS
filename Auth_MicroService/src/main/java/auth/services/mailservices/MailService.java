package auth.services.mailservices;

import auth.utils.ImageToDataConverter;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class MailService {
    @Autowired
    MailSenderImpl mailSender;

    public void sendSignupEmail(String to, String subject, BufferedImage Barcode, String username) throws MessagingException, IOException {
        DataSource imageDataSource = ImageToDataConverter.createImageDataSource(Barcode);
        StringBuilder htmlMailBody = new StringBuilder();
        htmlMailBody.append("<html><body>")
                .append("<p>Dear ").append(username).append(",</p>")
                .append("<p>Thank you for signing up! Your account has been successfully created.</p>")
                .append("<p>Please find below the barcode containing your account information:</p>")
                .append("<img src=\"cid:barcodeId\" alt=\"Barcode\">")
                .append("<p>You can use this QRcode to easily sign in to your account in the future. Simply scan the barcode.</p>")
                .append("<p>If you have any questions or need further assistance, please feel free to contact our support team.</p>")
                .append("<p>Best regards,</p>")
                .append("<p>TonKawa</p>")
                .append("</body></html>");
        mailSender.sendEmailWithInlineDataSource(to, subject, htmlMailBody.toString(), true, imageDataSource, "barcodeId");
    }
}
