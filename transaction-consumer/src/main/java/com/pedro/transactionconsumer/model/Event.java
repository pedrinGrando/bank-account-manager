package com.pedro.transactionconsumer.model;

import com.pedro.transactionconsumer.enums.EventType;
import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EventType eventType;

    public Event(Long id, EventType eventType) {
        this.id = id;
        this.eventType = eventType;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}