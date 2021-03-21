package com.dsa.subscription.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
