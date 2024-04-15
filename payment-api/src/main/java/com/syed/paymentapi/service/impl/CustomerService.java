package com.syed.paymentapi.service.impl;

import com.syed.paymentapi.entity.Customer;
import com.syed.paymentapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }
}
