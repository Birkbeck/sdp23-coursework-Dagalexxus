package sml.exceptions;

public class OpcodeNotFoundException extends RuntimeException {
    public OpcodeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

