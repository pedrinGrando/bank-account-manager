package com.pedro.accountsservice.service;

import com.pedro.accountsservice.dto.AccountDTO;
import com.pedro.accountsservice.dto.DepositInputRequest;
import com.pedro.accountsservice.dto.TransferInputRequest;
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
import java.security.SecureRandom;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class AccountsService {

    private static final String ACCOUNT_TOPIC = "accounts-topic";
    private static final String ACCOUNT_PREFIX = "ACC";
    private static final int ACCOUNT_NUMBER_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 1000;

    private final AccountsRepository accountsRepository;

    private final SecureRandom random = new SecureRandom();

    @Autowired
    private KafkaTemplate<String, AccountEvent> kafkaTemplate;

    public void createAccount(AccountDTO accDto) throws InterruptedException {
        String accountNumber = generateUniqueAccountNumber();
        Account account = new Account(accDto);
        account.setAccountNumber(accountNumber);
        accountsRepository.save(account);
        log.info("Account created: {}", account);
        Thread.sleep(1000);
        AccountEvent event = new AccountEvent(EventType.NEW_ACCOUNT, account);
        kafkaTemplate.send(ACCOUNT_TOPIC, event);
        log.info("Account event sent: {}", event);
    }

    private String generateUniqueAccountNumber() {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            String randomNumber = String.format("%0" + ACCOUNT_NUMBER_LENGTH + "d", random.nextInt((int) Math.pow(10, ACCOUNT_NUMBER_LENGTH)));
            String accountNumber = ACCOUNT_PREFIX + randomNumber;
            if (!accountsRepository.existsByAccountNumber(accountNumber)) {
                return accountNumber;
            }
        }
        throw new RuntimeException("Failed to generate a unique account number after " + MAX_ATTEMPTS + " attempts.");
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
            kafkaTemplate.send(ACCOUNT_TOPIC, new AccountEvent(EventType.DEPOSIT, account));
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
            kafkaTemplate.send(ACCOUNT_TOPIC, event);
        }

        log.info("Withdraw request: {}", accepted ? "Accepted" : "Rejected");
        return accepted;
    }

    public boolean transfer(TransferInputRequest request) {
        Account accFrom = accountsRepository.findIsActiveById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Account accTo = accountsRepository.findIsActiveById(request.getDestinationAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        BigDecimal withdrawAmount = request.getValue();

        boolean accepted = !accFrom.isNegative() && !accTo.isNegative()
                && accFrom.getBalance().compareTo(withdrawAmount) >= 0;

        if (accepted) {
            accFrom.setBalance(accFrom.getBalance().subtract(withdrawAmount));
            accountsRepository.save(accFrom);
            accTo.setBalance(accTo.getBalance().add(withdrawAmount));
            accountsRepository.save(accTo);
            AccountEvent event = new AccountEvent(EventType.TRANSFER, accTo);
            kafkaTemplate.send(ACCOUNT_TOPIC, event);
        }

        log.info("Transfer request: {}", accepted ? "Accepted" : "Rejected");
        return accepted;
    }
}
