package com.syed.userserviceapi.repository;


import com.syed.userserviceapi.entity.Card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Long> {

    boolean existsByEncryptedCardNumber(String encryptedCardNumber);

    List<Card> findByCustomer_Uuid(UUID uuid);
}
