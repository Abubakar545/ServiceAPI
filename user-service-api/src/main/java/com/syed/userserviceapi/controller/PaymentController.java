package com.syed.userserviceapi.controller;// PaymentController.java

import com.syed.userserviceapi.dto.PaymentRequest;
import com.syed.userserviceapi.dto.PaymentResponse;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.service.CustomerService;
import com.syed.userserviceapi.service.impl.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl paymentServiceImpl;
    private final CustomerService customerService;

    @PostMapping("/make/{uuid}")
    public ResponseEntity<?> makePayment(@PathVariable UUID uuid, @RequestBody PaymentRequest paymentRequest) {
        Customer customer = customerService.getCustomerByUUID(uuid);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        PaymentResponse paymentResponse = paymentServiceImpl.makePayment(customer, paymentRequest);

        return ResponseEntity.ok(paymentResponse);
    }
}

