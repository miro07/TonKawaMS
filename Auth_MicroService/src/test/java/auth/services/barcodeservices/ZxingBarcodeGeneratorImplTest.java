package auth.services.barcodeservices;

import auth.services.barcodeservices.ZxingBarcodeGeneratorImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ZxingBarcodeGeneratorImplTest {
    @Mock
    private QRCodeWriter qrCodeWriter;

    @InjectMocks
    private ZxingBarcodeGeneratorImpl zxingBarcodeGenerator;

    @Test
    public void generate() throws WriterException {
        String json = "test";
        int width = 200;
        int height = 200;

        BitMatrix bitMatrix = new BitMatrix(width, height);

        when(qrCodeWriter.encode(anyString(), any(BarcodeFormat.class), anyInt(), anyInt()))
                .thenReturn(bitMatrix);

        BufferedImage resultImage = zxingBarcodeGenerator.generate(json, width, height);

        assertNotNull(resultImage);
    }

}
