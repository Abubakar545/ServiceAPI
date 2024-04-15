package com.syed.userserviceapi.repository;

// PaymentRepository.java

import com.syed.userserviceapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Add custom methods if needed
}
