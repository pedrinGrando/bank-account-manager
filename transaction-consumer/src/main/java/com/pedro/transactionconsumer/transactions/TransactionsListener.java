package com.pedro.transactionconsumer.transactions;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
@RequiredArgsConstructor
@Log4j2
public class TransactionsListener {

    @SneakyThrows
    @KafkaListener(topics = "accounts-topic", groupId = "group-1", containerFactory = "jsonContainerFactory")
    public void transactionsMonitor() {
        log.info("Transaction received.");
        sleep(2000);
        log.info("Validating scam...");

        log.info("Transaction approved.");
        sleep(3000);
    }
}
