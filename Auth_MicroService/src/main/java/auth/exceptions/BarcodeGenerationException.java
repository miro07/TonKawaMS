package auth.exceptions;

public class BarcodeGenerationException extends RuntimeException {

    public BarcodeGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
