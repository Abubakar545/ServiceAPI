package com.syed.userserviceapi.repository;

import com.syed.userserviceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    // Correct the method name to match the property name
    boolean existsByUserName(String userName);
    Customer findByUserName(String userName);

    // Add a method to find a customer by UUID
    Customer findByUuid(UUID uuid);
}


