package peaksoft.java5.exceptions;

/**
 * @author Beksultan
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
