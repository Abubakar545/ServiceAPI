package com.syed.userserviceapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "UserName is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true) // Ensure username uniqueness
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one special character, one uppercase letter, and one digit")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true) // Ensure email uniqueness
    private String email;

}
