package com.pedro.accountsservice.repository;

import com.pedro.accountsservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findIsActiveById(Long accountId);
}