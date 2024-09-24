package com.pedro.transactionconsumer.transactions;

import com.pedro.transactionconsumer.model.Event;
import com.pedro.transactionconsumer.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
@RequiredArgsConstructor
@Log4j2
public class TransactionsListener {

    private final TransactionsService transactionsService;

    @SneakyThrows
    @KafkaListener(topics = "accounts-topic", groupId = "group-1", containerFactory = "jsonContainerFactory")
    public void transactionsMonitor(Event event) {
        log.info("Transaction received. {}" , event);
        sleep(2000);
        log.info("Validating scam...");
        transactionsService.validate(event);
        log.info("Transaction approved.");
        sleep(3000);
    }
}
