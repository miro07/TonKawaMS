package auth.utils;

import jakarta.mail.util.ByteArrayDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageToDataConverter {
    public static ByteArrayDataSource createImageDataSource(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return new ByteArrayDataSource(imageBytes, "image/png");
    }
}
