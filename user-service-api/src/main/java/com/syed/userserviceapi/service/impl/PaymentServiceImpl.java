package com.syed.userserviceapi.service.impl;

import com.syed.userserviceapi.dto.PaymentRequest;
import com.syed.userserviceapi.dto.PaymentResponse;
import com.syed.userserviceapi.entity.Account;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.entity.Payment;
import com.syed.userserviceapi.exception.PaymentException;
import com.syed.userserviceapi.repository.AccountRepository;
import com.syed.userserviceapi.repository.PaymentRepository;
import com.syed.userserviceapi.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    public PaymentResponse makePayment(Customer customer, PaymentRequest paymentRequest) {
        try {
            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setAmount(paymentRequest.getAmount());
            // Set bankAccount to account_number from Account entity
            Account account = accountRepository.findByCustomerUuid(customer.getUuid());
            if (account != null) {
                payment.setBankAccount(account.getAccountNumber());
            } else {
                throw new PaymentException("Account not found for customer: " + customer.getUuid());
            }
            // Generate UUID for requestID
            payment.setRequestID(UUID.randomUUID().toString());

            Payment savedPayment = paymentRepository.save(payment);

            PaymentResponse paymentResponse = new PaymentResponse();
            // Inside your makePayment method
            if (savedPayment != null) {
                BigDecimal amount = BigDecimal.valueOf(paymentRequest.getAmount());
                boolean deductionSuccess = deductAmountFromAccount(customer.getUuid(), amount);
                if (deductionSuccess) {
                    paymentResponse.setMessage("Payment successful");
                    paymentResponse.setTransactionDetails(savedPayment);
                } else {
                    paymentResponse.setMessage("Payment successful, but failed to update account balance");
                }
            } else {
                paymentResponse.setMessage("Payment failed");
            }

            return paymentResponse;
        } catch (PaymentException e) {
            log.error("Payment failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during payment process: {}", e.getMessage());
            throw new PaymentException("Payment failed due to an unexpected error");
        }
    }

    private boolean deductAmountFromAccount(UUID customerId, BigDecimal amount) {
        try {
            Account account = accountRepository.findByCustomerUuid(customerId);
            if (account != null) {
                BigDecimal currentBalance = account.getCurrentBalance();
                BigDecimal newBalance = currentBalance.subtract(amount);
                if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                    account.setCurrentBalance(newBalance);
                    accountRepository.save(account);
                    return true;
                } else {
                    throw new PaymentException("Insufficient funds in the account");
                }
            } else {
                throw new PaymentException("Account not found for customer: " + customerId);
            }
        } catch (PaymentException e) {
            log.error("Deduction from account failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during deduction from account: {}", e.getMessage());
            throw new PaymentException("Failed to deduct amount from account");
        }
    }
}
