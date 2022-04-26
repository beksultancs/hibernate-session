package peaksoft.java5.exceptions;

/**
 * @author Beksultan
 */
public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException() {
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
