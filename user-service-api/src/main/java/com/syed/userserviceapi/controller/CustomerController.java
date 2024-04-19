package com.syed.userserviceapi.controller;

import com.syed.userserviceapi.dto.AccountsContactInfoDto;
import com.syed.userserviceapi.entity.Card;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;


    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;


    // Endpoint to fetch customer details by UUID
    @GetMapping("/{uuidString}")
    public ResponseEntity<?> getCustomerByUuid(@PathVariable("uuidString") String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            Customer customer = customerService.getCustomerByUUID(uuid);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found for UUID: " + uuid);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID string: " + uuidString);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/cards/{uuid}")
    public ResponseEntity<List<Card>> getCardsByCustomerUuid(@PathVariable("uuid") UUID uuid) {
        List<Card> cards = customerService.getCardsByCustomerUuid(uuid);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }


    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("MAVEN_HOME"));
    }



    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
