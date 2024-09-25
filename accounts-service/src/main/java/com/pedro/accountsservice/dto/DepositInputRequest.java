package com.pedro.accountsservice.dto;

import java.math.BigDecimal;

public class DepositInputRequest {

    private Long accountId;
    private BigDecimal value;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
