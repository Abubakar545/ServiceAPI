package com.syed.userserviceapi.service;

import com.syed.userserviceapi.dto.UserRegistrationRequestDTO;
import com.syed.userserviceapi.dto.UserRegistrationResponseDTO;

public interface RegistrationService {
    UserRegistrationResponseDTO registerUser(UserRegistrationRequestDTO request);
}
