package com.pedro.accountsservice.model;

import com.pedro.accountsservice.dto.AccountDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    private String accountHolder;

    private BigDecimal balance;

    private boolean active;

    private boolean negative;

    public Account() {
    }

    public Account(String accountNumber, String accountHolder, BigDecimal balance, boolean active, boolean negative) {
    }

    public Account(AccountDTO accDto) {
        this.accountHolder = accDto.getAccountHolder();
        this.balance = accDto.getBalance();
        this.active = accDto.isActive();
        this.negative = accDto.isNegative();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                ", negative=" + negative +
                '}';
    }
}