package com.fmatheus.app.hexagonal.application.domain.exception;

public class ValidationNotNullException extends RuntimeException {

    public ValidationNotNullException(String message) {
        super(message);
    }

}
