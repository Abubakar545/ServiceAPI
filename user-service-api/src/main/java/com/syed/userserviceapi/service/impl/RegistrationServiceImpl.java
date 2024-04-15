package com.syed.userserviceapi.service.impl;

import com.syed.userserviceapi.constants.UserServiceConstants;
import com.syed.userserviceapi.dto.CardDetailsDTO;
import com.syed.userserviceapi.dto.UserRegistrationRequestDTO;
import com.syed.userserviceapi.dto.UserRegistrationResponseDTO;
import com.syed.userserviceapi.entity.Card;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.exception.CardDetailsAlreadyExistsException;
import com.syed.userserviceapi.exception.InvalidRegistrationRequestException;
import com.syed.userserviceapi.exception.UsernameAlreadyExistsException;
import com.syed.userserviceapi.repository.CardRepository;
import com.syed.userserviceapi.repository.CustomerRepository;
import com.syed.userserviceapi.service.RegistrationService;
import com.syed.userserviceapi.service.UserDirectoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;
    private final UserDirectoryService userDirectoryService;

    @Override
    @Transactional
    public UserRegistrationResponseDTO registerUser(UserRegistrationRequestDTO request) {
        // Validate the registration request
        if (request == null || !request.isValid()) {
            throw new InvalidRegistrationRequestException("Invalid registration request");
        }

        // Check if the username already exists
        // Check if the request and the username are not null
        if (request != null && request.getUserName() != null && customerRepository.existsByUserName(request.getUserName())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // Check if the card details are already associated with another customer
        if (cardRepository.existsByEncryptedCardNumber(request.getCardDetailsDTO().getCardNumber())) {
            throw new CardDetailsAlreadyExistsException("Card details are already associated with another customer");
        }

        // Save customer data in the database
        Customer customer = new Customer();
        customer.setUserName(request.getUserName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setCustomerName(request.getCustomerName());
        customerRepository.save(customer);

        // Save card data in the database
        CardDetailsDTO cardDetailsDTO = request.getCardDetailsDTO();
        Card card = new Card();
        card.setCustomer(customer);
        card.setEncryptedCardNumber(cardDetailsDTO.getCardNumber());
        card.setEncryptedCvv(cardDetailsDTO.getCvv());
        card.setExpiryDate(cardDetailsDTO.getExpiryDate());
        cardRepository.save(card);

        // Save user details in the user directory
        userDirectoryService.saveUser(customer.getUserName());

        // Create and return the registration response
        UserRegistrationResponseDTO response = new UserRegistrationResponseDTO();
        response.setStatusCode(UserServiceConstants.STATUS_201);
        response.setStatusMsg(UserServiceConstants.MESSAGE_201);
        return response;
    }
}
