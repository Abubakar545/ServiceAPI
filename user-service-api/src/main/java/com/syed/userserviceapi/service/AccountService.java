package com.syed.userserviceapi.service;

import com.syed.userserviceapi.entity.Account;

import java.util.UUID;

public interface AccountService {
//    Account updateAccount(Account account);
    public Account createAccount(Account account, String customerUuid);
}
