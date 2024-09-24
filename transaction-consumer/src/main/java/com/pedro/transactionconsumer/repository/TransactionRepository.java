package com.pedro.transactionconsumer.repository;

import com.pedro.transactionconsumer.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Event, Long> {
}
