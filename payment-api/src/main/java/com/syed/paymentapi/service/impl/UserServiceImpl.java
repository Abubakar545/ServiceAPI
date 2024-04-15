//package com.syed.paymentapi.service.impl;
//
//import com.syed.paymentapi.dto.UserRegistrationRequestDTO;
//import com.syed.paymentapi.dto.UserRegistrationResponseDTO;
//import com.syed.paymentapi.entity.Account;
//import com.syed.paymentapi.entity.Card;
//import com.syed.paymentapi.exception.RegistrationException;
//import com.syed.paymentapi.repository.AccountRepository;
//import com.syed.paymentapi.repository.CardRepository;
//import com.syed.paymentapi.service.IUserService;
//import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service @AllArgsConstructor
//public class UserServiceImpl implements IUserService {
//
//    private final CardRepository cardRepository;
//    private final AccountRepository accountRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public UserRegistrationResponseDTO registerUser(UserRegistrationRequestDTO userRequestDTO) {
//        try {
//            // Perform user registration logic here
//            // Assuming you need to save user data and create an account
//
//            // Create a new card entity
//            Card card = new Card();
//            card.setCardNumber(userRequestDTO.getCardDetails().getCardNumber());
//            card.setCvv(userRequestDTO.getCardDetails().getCvv());
//            card.setExpiryDate(userRequestDTO.getCardDetails().getExpiryDate());
//
//            // Save the card entity to get the ID
//            card = cardRepository.save(card);
//
//            // Create a new account entity
//            Account account = new Account();
//            account.setUsername(userRequestDTO.getUsername());
//            // Encode the password before saving
//            account.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
//            account.setEmail(userRequestDTO.getEmail());
//            account.setCustomerName(userRequestDTO.getCustomerName());
//            // Set the card for the account
//            account.setCard(card);
//
//            // Save the account entity
//            account = accountRepository.save(account);
//
//            // Prepare the response
//            UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO();
//            responseDTO.setMessage("User registration successful");
//            // You can add more fields to the response if needed
//
//            return responseDTO;
//        } catch (Exception ex) {
//            // If an error occurs, handle it here
//            throw new RegistrationException("User registration failed: " + ex.getMessage());
//        }
//    }
//
//}
