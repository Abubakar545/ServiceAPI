package com.syed.paymentapi.exception;

public class CardDetailsAlreadyExistsException extends RuntimeException {

//    public CardDetailsAlreadyExistsException() {
//        super();
//    }

    public CardDetailsAlreadyExistsException(String message) {
        super(message);
    }

//    public CardDetailsAlreadyExistsException(String message, Throwable cause) {
//        super(message, cause);
//    }
}
