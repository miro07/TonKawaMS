package auth.utils;

import auth.utils.ImageToDataConverter;
import jakarta.mail.util.ByteArrayDataSource;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ImageToDataConverterTest {
    @Test
    public void  createImageDataSource() throws IOException {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        ByteArrayDataSource dataSource = ImageToDataConverter.createImageDataSource(image);

        assertNotNull(dataSource);
        assertEquals("image/png", dataSource.getContentType());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        byte[] expectedImageBytes = baos.toByteArray();
        byte[] actualImageBytes = dataSource.getInputStream().readAllBytes();

        assertArrayEquals(expectedImageBytes, actualImageBytes);


    }
}
