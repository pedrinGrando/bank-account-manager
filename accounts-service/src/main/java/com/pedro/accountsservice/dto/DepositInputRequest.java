package com.pedro.accountsservice.dto;

import java.math.BigDecimal;

public class DepositInputRequest {

    private String accountNumber;
    private BigDecimal value;

    public String getAccountNumber () {
        return accountNumber;
    }

    public void accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
