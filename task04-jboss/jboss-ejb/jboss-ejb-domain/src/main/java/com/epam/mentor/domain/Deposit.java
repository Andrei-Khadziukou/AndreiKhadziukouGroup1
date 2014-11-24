package com.epam.mentor.domain;

import java.math.BigDecimal;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14
 */
public class Deposit {

    private DepositKey depositKey;
    private BigDecimal value;

    public Deposit(String accountId, String currency, BigDecimal value) {
        this(new DepositKey(accountId, currency), value);
    }

    public Deposit(DepositKey depositKey, BigDecimal value) {
        this.depositKey = depositKey;
        this.value = value;
    }

    public DepositKey getDepositKey() {
        return depositKey;
    }

    public void setDepositKey(DepositKey depositKey) {
        this.depositKey = depositKey;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
