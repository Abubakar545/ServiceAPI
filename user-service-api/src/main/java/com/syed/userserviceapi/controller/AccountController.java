package com.syed.userserviceapi.controller;

import com.syed.userserviceapi.entity.Account;
import com.syed.userserviceapi.exception.CustomerNotFoundException;
import com.syed.userserviceapi.exception.DuplicateMobileNumberException;
import com.syed.userserviceapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/update")
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account, String customerUuid) {
        try {
            Account createdAccount = accountService.createAccount(account, customerUuid);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found for UUID: " + customerUuid);
        } catch (DuplicateMobileNumberException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dublicate Mobile number not allowed for "+ customerUuid );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

