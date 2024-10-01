package com.pedro.accountsservice.repository;

import com.pedro.accountsservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(String accountNumber);

    Optional<Account> findByAccountHolder(String accountHolder);

    Optional<Account> findIsActiveByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.balance <= 0 OR a.active = false")
    ArrayList<Account> findAllInactiveAccounts();
}