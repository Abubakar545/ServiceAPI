package com.syed.userserviceapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDTO {

    @NotBlank(message = "UserName is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "username", unique = true) // Ensure username uniqueness
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).*$", message = "Password must contain at least one special character")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true) // Ensure email uniqueness
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
