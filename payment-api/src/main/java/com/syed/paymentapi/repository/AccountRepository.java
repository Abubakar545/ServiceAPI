package com.syed.paymentapi.repository;

import com.syed.paymentapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Add custom query methods if needed
}
