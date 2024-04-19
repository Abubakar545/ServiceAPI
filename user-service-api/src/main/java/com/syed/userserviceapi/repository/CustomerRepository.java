package com.syed.userserviceapi.repository;

import com.syed.userserviceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    // Correct the method name to match the property name
    boolean existsByUsername(String username);

//    Customer findByUsername(String username);  // commented this part

    // Add a method to find a customer by UUID
    Customer findByUuid(UUID uuid);


    Optional<Customer> findByUsername(String username);


}


