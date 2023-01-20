package com.drei.os.services.exceptions;

public class DataViolationIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataViolationIntegrityException(String message) {
        super(message);
    }

    public DataViolationIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
