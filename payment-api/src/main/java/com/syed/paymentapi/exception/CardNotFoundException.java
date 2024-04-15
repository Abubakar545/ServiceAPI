package com.syed.paymentapi.exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String message){
        super(message);
    }

}
