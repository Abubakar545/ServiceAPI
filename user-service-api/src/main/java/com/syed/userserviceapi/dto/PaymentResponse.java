package com.syed.userserviceapi.dto;

import com.syed.userserviceapi.entity.Payment;
import lombok.Data;

@Data
public class PaymentResponse {
    private String message;
    private Payment transactionDetails;
}
