// CustomerService.java
package com.syed.userserviceapi.service;

import com.syed.userserviceapi.entity.Card;
import com.syed.userserviceapi.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<Customer> getCustomerByUsername(String username);
    Customer getCustomerByUUID(UUID id);

    List<Card> getCardsByCustomerUuid(UUID id);

}
