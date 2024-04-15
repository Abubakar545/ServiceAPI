package com.syed.paymentapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationRequestDTO {
    @NotBlank(message = "Name is required")
    private String userName;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Valid
    private CardDetailsDTO cardDetailsDTO;

    public boolean isValid() {
        return userName != null && !userName.isEmpty() &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty() &&
                customerName != null && !customerName.isEmpty() &&
                cardDetailsDTO != null && cardDetailsDTO.isValid();
    }

}
