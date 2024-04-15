package com.syed.userserviceapi.service;

import com.syed.userserviceapi.dto.PaymentRequest;
import com.syed.userserviceapi.dto.PaymentResponse;
import com.syed.userserviceapi.entity.Customer;

public interface PaymentService {

    public PaymentResponse makePayment(Customer customer, PaymentRequest paymentRequest);

}
