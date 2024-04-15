// CustomerService.java
package com.syed.userserviceapi.service;

import com.syed.userserviceapi.entity.Card;
import com.syed.userserviceapi.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerByUserName(String userName);
    Customer getCustomerByUUID(UUID uuid);

    List<Card> getCardsByCustomerUuid(UUID uuid);

}
