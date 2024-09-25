package com.pedro.accountsservice.service;

import com.pedro.accountsservice.dto.DepositInputRequest;
import com.pedro.accountsservice.dto.WithdrawInputRequest;
import com.pedro.accountsservice.enums.EventType;
import com.pedro.accountsservice.model.Account;
import com.pedro.accountsservice.model.AccountEvent;
import com.pedro.accountsservice.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    private KafkaTemplate<String, AccountEvent> kafkaTemplate;

    public void createAccount(Account account) throws InterruptedException {
        accountsRepository.save(account);
        log.info("Account created: {}", account);
        Thread.sleep(1000);
        AccountEvent event = new AccountEvent(EventType.NEW_ACCOUNT, account);
        kafkaTemplate.send("accounts-topic", event);
        log.info("Account created: {}", event);
    }

    public List<Account> findAll() {
        List<Account> accounts = accountsRepository
                .findAll()
                .stream()
                .filter(Account::isActive)
                .toList();
        return accounts;
    }

    public boolean deposit(DepositInputRequest request) {
        Account account = accountsRepository.findIsActiveById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        boolean accepted = !account.isNegative();

        if (accepted) {
            account.setBalance(account.getBalance().add(request.getValue()));
            accountsRepository.save(account);
            kafkaTemplate.send("accounts-topic", new AccountEvent(EventType.DEPOSIT, account));
        }

        log.info("Deposit request: {}", accepted ? "Accepted" : "Rejected");
        return accepted;
    }

    public boolean withdraw(WithdrawInputRequest request) {
        Account account = accountsRepository.findIsActiveById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        BigDecimal withdrawAmount = request.getValue();

        boolean accepted = !account.isNegative()
                && account.getBalance().compareTo(withdrawAmount) >= 0;

        if (accepted) {
            account.setBalance(account.getBalance().subtract(withdrawAmount));
            accountsRepository.save(account);
            AccountEvent event = new AccountEvent(EventType.WITHDRAWAL, account);
            kafkaTemplate.send("accounts-topic", event);
        }

        log.info("Withdraw request: {}", accepted ? "Accepted" : "Rejected");
        return accepted;
    }
}
