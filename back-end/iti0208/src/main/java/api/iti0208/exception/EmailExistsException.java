package api.iti0208.exception;


public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }
}