package com.syed.paymentapi.service;


import com.syed.paymentapi.dto.UserRegistrationRequestDTO;
import com.syed.paymentapi.dto.UserRegistrationResponseDTO;

public interface RegistrationService {
    UserRegistrationResponseDTO registerUser(UserRegistrationRequestDTO request);
}
