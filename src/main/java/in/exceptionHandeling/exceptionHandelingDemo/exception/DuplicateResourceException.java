package in.exceptionHandeling.exceptionHandelingDemo.exception;

public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String message) {
        super(message);
    }
}
