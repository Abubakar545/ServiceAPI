package com.syed.userserviceapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be a 10-digit number")
    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\+[0-9]{1,3}-[0-9]{3,14}$", message = "Invalid mobile number format")
    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

    @ManyToOne(fetch = FetchType.EAGER) // Each account belongs to one customer
    @JoinColumn(name = "customer_id", nullable = false) // Name of the foreign key column in accounts table
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "account_limit", precision = 10, scale = 2)
    private BigDecimal limit;

    @Column(name = "current_balance", precision = 10, scale = 2)
    private BigDecimal currentBalance;
}
