package com.syed.userserviceapi.exception;

public class InvalidCustomerDataException extends RuntimeException{
    public InvalidCustomerDataException(String message){
        super(message);
    }
}
