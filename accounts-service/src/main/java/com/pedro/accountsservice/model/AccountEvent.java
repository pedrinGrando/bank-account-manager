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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}