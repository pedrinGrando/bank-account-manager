package com.pedro.accountsservice.model;

import com.pedro.accountsservice.enums.EventType;
import jakarta.persistence.*;

@Entity
public class AccountEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EventType eventType;
    @ManyToOne
    private Account account;

    public AccountEvent(EventType eventType, Account account) {
        this.eventType = eventType;
        this.account = account;
    }

    public AccountEvent() {
    }
}