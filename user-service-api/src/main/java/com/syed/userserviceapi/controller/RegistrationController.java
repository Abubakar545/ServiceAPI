package com.syed.userserviceapi.controller;

import com.syed.userserviceapi.dto.UserRegistrationRequestDTO;
import com.syed.userserviceapi.dto.UserRegistrationResponseDTO;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.exception.UsernameAlreadyExistsException;
import com.syed.userserviceapi.exception.CardValidationException;
import com.syed.userserviceapi.service.RegistrationService;
import com.syed.userserviceapi.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final CustomerService customerService;


    @PostMapping("/api/v1/users/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed:\n");
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }
        try {
            UserRegistrationResponseDTO response = registrationService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } catch (CardValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred "+e.getMessage());
        }
    }


    @GetMapping("/api/v1/users/{username}")
    public ResponseEntity<?> getCustomerByUsername(@PathVariable String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is required");
        }

        try {
            Customer customer = customerService.getCustomerByUserName(username);
            if (customer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found for username: " + username);
            }
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
