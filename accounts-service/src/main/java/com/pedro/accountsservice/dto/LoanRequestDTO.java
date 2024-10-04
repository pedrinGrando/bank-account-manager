package com.pedro.accountsservice.dto;

import java.math.BigDecimal;

public class LoanRequestDTO {

    private String accountNumber;
    private String reason;
    private BigDecimal value;

    public LoanRequestDTO(String accountNumber, BigDecimal value, String reason) {
        this.accountNumber = accountNumber;
        this.value = value;
        this.reason = reason;
    }

    public LoanRequestDTO() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
