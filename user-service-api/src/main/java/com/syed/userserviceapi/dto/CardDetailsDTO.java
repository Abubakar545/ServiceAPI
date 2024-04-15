package com.syed.userserviceapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CardDetailsDTO {
    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Invalid card number. It should be a 16-digit number.\n")
    private String cardNumber;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "\\d{3}", message = "Invalid CVV. It should be a 3-digit number.\n")
    private String cvv;

    @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Invalid expiry date. It should be in the format MM/YY and the month should be between 01 and 12.")
    private String expiryDate;


    public boolean isValid() {
        // Custom validation logic for card details
        return cardNumber != null && cardNumber.matches("\\d{16}") &&
                cvv != null && cvv.matches("\\d{3}") &&
                expiryDate != null && expiryDate.matches("\\d{2}/\\d{2}");
    }
}
