//package com.syed.paymentapi.controller;
//
//import com.syed.paymentapi.dto.PaymentRequestDTO;
//import com.syed.paymentapi.dto.PaymentResponseDTO;
//import com.syed.paymentapi.service.IPaymentService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
///**
// * PaymentController handles API requests related to payment processing.
// * This includes one-time payment link functionality.
// */
//@RestController
//@RequestMapping(path = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
//public class PaymentController {
//
//    private final IPaymentService paymentService;
//
//    public PaymentController(IPaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    /**
//     * Handles one-time payment link requests.
//     *
//     * @param paymentRequestDTO Payment request details including access token, request ID,
//     *                          card ID, amount, currency, optional additional details,
//     *                          and (assumed) a field for payment link UUID.
//     * @return ResponseEntity containing payment processing response or error message
//     *         with a "Payment-Link" header in the successful response.
//     */
//    @PostMapping("/one-time-link")
//    public ResponseEntity<?> makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
//
//        // Generate a UUID for the transaction
//        UUID generatedUUID = UUID.randomUUID();
//        paymentRequestDTO.setPaymentLinkUuid(generatedUUID.toString());  // Assuming DTO has a field for UUID
//
//        // Process payment request with generated UUID
//        PaymentResponseDTO response = paymentService.makePayment(paymentRequestDTO);
//
//        // Check if payment was successful
//        if (response != null && response.getTransactionDetails().getStatus().equals("successful")) {
//            // Generate payment link URL (replace with your actual logic)
//            String paymentLinkUrl = generatePaymentLinkUrl(paymentRequestDTO.getCustomerId(), generatedUUID);
//
//            // Create a new HttpHeaders object and add payment link URL as a header
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Payment-Link", paymentLinkUrl);
//
//            // Return ResponseEntity with headers and successful response
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(response);
//        } else {
//            // Payment failed or insufficient funds
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("{\"message\": \"Payment failed or insufficient funds\"}");
//        }
//    }
//
//    // ** Replace with your actual URL construction logic **
//    private String generatePaymentLinkUrl(Long customerId, UUID generatedUUID) {
//        return "/payments/link/" + customerId + "/" + generatedUUID;  // Placeholder
//    }
//
//    // ** Replace with your actual logic to extract UUID from payment link **
//    private UUID getUuidFromPaymentLink(HttpServletRequest request) {
//        // Extract UUID from request path or query parameters
//        // ... Implement your extraction logic here
//        return null;
//    }
//}
