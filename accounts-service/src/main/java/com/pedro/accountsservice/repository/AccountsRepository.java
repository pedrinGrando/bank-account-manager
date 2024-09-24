package com.pedro.accountsservice.repository;

import com.pedro.accountsservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account, Long> {
}