package com.syed.userserviceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class PaymentLinkNotFoundException extends RuntimeException {

    public PaymentLinkNotFoundException(String message) {
        super(message);
    }
}
