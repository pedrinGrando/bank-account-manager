package com.pedro.transactionconsumer.service;

import com.pedro.transactionconsumer.model.Event;
import com.pedro.transactionconsumer.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class TransactionsService {

    private final TransactionRepository transactionRepository;

    public void validate(Event event) {
        if (event != null) {
            transactionRepository.save(event);
        }
        log.error("Transactions not received.");
    }

}
