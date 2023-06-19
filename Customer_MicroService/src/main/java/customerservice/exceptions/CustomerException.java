package customerservice.exceptions;

public class CustomerException extends RuntimeException {

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
