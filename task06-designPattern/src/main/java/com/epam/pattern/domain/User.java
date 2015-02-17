package com.epam.pattern.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.math.BigDecimal;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class User extends StorageDomain {

    private BigDecimal balance = BigDecimal.ZERO;

    public User() {
        // default ctor.
    }

    public User(String id) {
        setId(id);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            User user = (User) obj;
            return new EqualsBuilder().append(balance, user.balance).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
