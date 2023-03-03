package sml.exceptions;

public class DuplicateLabelException extends RuntimeException {
    public DuplicateLabelException(String errorMessage) {
        super(errorMessage);
    }
}

