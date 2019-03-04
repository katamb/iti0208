package api.iti0208.exception;


public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String message) {
        super(message);
    }
}