package auth.services.barcodeservices;

import auth.exceptions.BarcodeGenerationException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class ZxingBarcodeGeneratorImpl implements BarcodeGeneratorStrategy {

    @Override
    public BufferedImage generate(String jsonData, int width, int height) {
       try{
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(jsonData, BarcodeFormat.QR_CODE, width, height);

            return MatrixToImageWriter.toBufferedImage(bitMatrix);

        } catch(Exception e) {
           throw new BarcodeGenerationException("QRcode generation failed. Error: " + e.getMessage(), e);
        }

}
}
