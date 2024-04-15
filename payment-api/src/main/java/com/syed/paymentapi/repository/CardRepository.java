package com.syed.paymentapi.repository;

import com.syed.paymentapi.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    // Add custom query methods if needed
    boolean existsByEncryptedCardNumber(String encryptedCardNumber);
}

