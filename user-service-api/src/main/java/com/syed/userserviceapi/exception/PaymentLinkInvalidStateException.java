package com.syed.userserviceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@SuppressWarnings("serial")
public class PaymentLinkInvalidStateException extends RuntimeException {

    public PaymentLinkInvalidStateException(String message) {
        super(message);
    }
}
