//package com.syed.paymentapi.service.impl;
//
//import com.syed.paymentapi.dto.PaymentRequestDTO;
//import com.syed.paymentapi.dto.PaymentResponseDTO;
//import com.syed.paymentapi.entity.Account;
//import com.syed.paymentapi.entity.Card;
//import com.syed.paymentapi.entity.Transaction;
//import com.syed.paymentapi.exception.CardNotFoundException;
//import com.syed.paymentapi.exception.InsufficientFundsException;
//import com.syed.paymentapi.repository.AccountRepository;
//import com.syed.paymentapi.repository.CardRepository;
//import com.syed.paymentapi.repository.TransactionRepository;
//import com.syed.paymentapi.service.IPaymentService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@AllArgsConstructor
//public class PaymentServiceImpl implements IPaymentService {
//
//    private final CardRepository cardRepository;
//    private final AccountRepository accountRepository;
//    private final TransactionRepository transactionRepository;
//
//    @Override
//    public PaymentResponseDTO makePayment(PaymentRequestDTO paymentRequest) {
//        Card card = retrieveCard(paymentRequest.getCardId());
//        updateAccountBalance(card, paymentRequest);
//        Transaction transaction = saveTransaction(card, paymentRequest);
//        return createPaymentResponse(transaction);
//    }
//
//    private Card retrieveCard(Long cardId) {
//        Optional<Card> optionalCard = cardRepository.findById(cardId);
//        if (optionalCard.isEmpty()) {
//            throw new CardNotFoundException("Card not found");
//        }
//        return optionalCard.get();
//    }
//
//    private void updateAccountBalance(Card card, PaymentRequestDTO paymentRequest) {
//        Account account = card.getAccount();
//        BigDecimal paymentAmount = paymentRequest.getAmount();
//        BigDecimal currentBalance = account.getBalance();
//        BigDecimal newBalance = currentBalance.subtract(paymentAmount);
//        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
//            throw new InsufficientFundsException("Insufficient funds");
//        }
//        account.setBalance(newBalance);
//        accountRepository.save(account);
//    }
//
//    private Transaction saveTransaction(Card card, PaymentRequestDTO paymentRequest) {
//        Account account = card.getAccount();
//        Transaction transaction = new Transaction();
//        transaction.setAccount(account);
//        transaction.setRequestId(paymentRequest.getRequestId()); // Assuming request ID is still used
//        transaction.setAmount(paymentRequest.getAmount());
//        transaction.setCurrency(paymentRequest.getCurrency());
//        transaction.setType("Payment");
//        transaction.setStatus("Successful"); // Update status later based on processing
//        transaction.setCreatedAt(LocalDateTime.now());
//
//        // Generate and set UUID
//        transaction.setTransactionId(UUID.randomUUID());
//
//        return transactionRepository.save(transaction);
//    }
//
//
//    private PaymentResponseDTO createPaymentResponse(Transaction transaction) {
//        return new PaymentResponseDTO("Payment successful", new PaymentResponseDTO.TransactionDetails(
//                transaction.getAmount(),
//                transaction.getCurrency(),
//                transaction.getStatus()
//        ));
//    }
//}
