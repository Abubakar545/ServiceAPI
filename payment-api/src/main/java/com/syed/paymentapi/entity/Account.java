package com.syed.paymentapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "accounts")
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false) // Use @JoinColumn instead of @Column for many-to-one association
    private Customer customer;

    @Column(name = "account_limit", precision = 10, scale = 2)
    private BigDecimal limit;

    @Column(name = "current_balance", precision = 10, scale = 2) // Adjusted column precision and scale
    private BigDecimal currentBalance;
}

