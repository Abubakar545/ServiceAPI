package com.syed.paymentapi.exception;

// InvalidRegistrationRequestException.java
public class InvalidRegistrationRequestException extends RuntimeException {
    public InvalidRegistrationRequestException(String message) {
        super(message);
    }
}