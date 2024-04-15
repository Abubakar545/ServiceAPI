// CustomerServiceImpl.java
package com.syed.userserviceapi.service.impl;

import com.syed.userserviceapi.entity.Card;
import com.syed.userserviceapi.entity.Customer;
import com.syed.userserviceapi.repository.CardRepository;
import com.syed.userserviceapi.repository.CustomerRepository;
import com.syed.userserviceapi.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;



    @Override
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }

    @Override
    public Customer getCustomerByUUID(UUID uuid) {
        return customerRepository.findByUuid(uuid);
    }
    @Override
    public List<Card> getCardsByCustomerUuid(UUID uuid) {
        return cardRepository.findByCustomer_Uuid(uuid);
    }
}
