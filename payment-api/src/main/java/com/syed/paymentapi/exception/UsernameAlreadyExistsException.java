package com.syed.paymentapi.exception;

// UsernameAlreadyExistsException.java
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}