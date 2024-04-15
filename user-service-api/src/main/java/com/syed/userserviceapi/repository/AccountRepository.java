package com.syed.userserviceapi.repository;

import com.syed.userserviceapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByCustomerUuid(UUID customerId);

    boolean existsByMobileNumber(String mobileNumber);
}
