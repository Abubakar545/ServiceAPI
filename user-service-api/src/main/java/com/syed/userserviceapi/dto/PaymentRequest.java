package com.syed.userserviceapi.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PaymentRequest {
    @Column(name = "debit_amount", nullable = false)
    private Double amount;
}

