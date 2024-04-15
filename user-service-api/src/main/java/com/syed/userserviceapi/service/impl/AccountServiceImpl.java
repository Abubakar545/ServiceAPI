package com.syed.userserviceapi.service.impl;

import com.syed.userserviceapi.entity.Account;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.exception.*;
import com.syed.userserviceapi.repository.AccountRepository;
import com.syed.userserviceapi.repository.CustomerRepository;
import com.syed.userserviceapi.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Account createAccount(Account account, String customerUuid) {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(UUID.fromString(customerUuid));
            if (customerOptional.isEmpty()) {
                throw new CustomerNotFoundException("Customer not found with ID: " + customerUuid);
            }else if (accountRepository.existsByMobileNumber(account.getMobileNumber())) {
                throw new DuplicateMobileNumberException("Duplicate mobile number: " + account.getMobileNumber());
            }

            Customer customer = customerOptional.get();
            account.setCustomer(customer); // Associate customer with account
            return accountRepository.save(account);
        } catch (DataIntegrityViolationException e) {
            // Check if the exception is due to a unique constraint violation
            if (e.getMessage().contains("UK_6kplolsdtr3slnvx97xsy2kc8")) {
                throw new AccountCreationException("Account with this account number already exists.");
            } else {
                // Handle other data integrity violations
                throw new AccountCreationException("Failed to create account due to a data integrity violation.");
            }
        }
    }
}
