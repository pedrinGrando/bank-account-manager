package com.pedro.accountsservice.service;

import com.pedro.accountsservice.enums.EventType;
import com.pedro.accountsservice.model.Account;
import com.pedro.accountsservice.model.AccountEvent;
import com.pedro.accountsservice.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.info("Event created: {}", event);
    }

    public List<Account> findAll() {
       List<Account> accounts = accountsRepository
               .findAll()
               .stream()
               .filter(a -> a.isActive())
               .toList();
        return accounts;
    }
}
