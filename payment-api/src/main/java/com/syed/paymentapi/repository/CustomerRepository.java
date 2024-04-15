package com.syed.paymentapi.repository;

import com.syed.paymentapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Correct the method name to match the property name
    boolean existsByUserName(String userName);
    Customer findByUserName(String userName);
}
