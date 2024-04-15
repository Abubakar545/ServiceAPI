package com.syed.paymentapi.service.impl;

import com.syed.paymentapi.constants.PaymentConstants;
import com.syed.paymentapi.dto.CardDetailsDTO;
import com.syed.paymentapi.dto.UserRegistrationRequestDTO;
import com.syed.paymentapi.dto.UserRegistrationResponseDTO;
import com.syed.paymentapi.entity.Card;
import com.syed.paymentapi.entity.Customer;
import com.syed.paymentapi.exception.CardDetailsAlreadyExistsException;
import com.syed.paymentapi.exception.InvalidRegistrationRequestException;
import com.syed.paymentapi.exception.UsernameAlreadyExistsException;
import com.syed.paymentapi.repository.CardRepository;
import com.syed.paymentapi.repository.CustomerRepository;
import com.syed.paymentapi.service.RegistrationService;
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
        userDirectoryService.saveUser(customer.getUserName(), customer.getPassword(), customer.getId());

        // Create and return the registration response
        UserRegistrationResponseDTO response = new UserRegistrationResponseDTO();
        response.setStatusCode(PaymentConstants.STATUS_201);
        response.setStatusMsg(PaymentConstants.MESSAGE_201);
        return response;
    }
}
