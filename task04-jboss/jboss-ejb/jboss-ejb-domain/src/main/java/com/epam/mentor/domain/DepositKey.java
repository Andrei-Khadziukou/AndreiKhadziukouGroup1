package com.epam.mentor.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14
 */
public class DepositKey {

    private String accountId;
    private String currency;

    public DepositKey(String accountId, String currency) {
        this.accountId = accountId;
        this.currency = currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(accountId)
                .append(currency)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        DepositKey key = (DepositKey) obj;
        return new EqualsBuilder()
                .append(accountId, key.accountId)
                .append(currency, key.currency)
                .isEquals();
    }
}
