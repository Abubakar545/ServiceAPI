package com.syed.userserviceapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Customer customer;

    private String encryptedCardNumber; // Encrypted card number using a strong algorithm (AES-256)

    private String encryptedCvv; // Encrypted CVV using a strong encryption algorithm (AES-256)

    private String expiryDate; // Store in YYYY-MM-DD format for easy validation

    private boolean isActive = true;
}
