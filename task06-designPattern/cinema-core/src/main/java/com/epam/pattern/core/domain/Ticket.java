package com.epam.pattern.core.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class Ticket extends StorageDomain {
    private BigDecimal cost;
    private Integer count;
    private Date sessionTimeStart;
    private Date sessionTimeEnd;

    public Ticket(String id, String sessionName, BigDecimal cost) {
        setId(id);
        setName(sessionName);
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getSessionTimeStart() {
        return sessionTimeStart;
    }

    public void setSessionTimeStart(Date sessionTimeStart) {
        this.sessionTimeStart = sessionTimeStart;
    }

    public Date getSessionTimeEnd() {
        return sessionTimeEnd;
    }

    public void setSessionTimeEnd(Date sessionTimeEnd) {
        this.sessionTimeEnd = sessionTimeEnd;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(super.hashCode())
                .append(count)
                .append(cost)
                .append(sessionTimeStart)
                .append(sessionTimeEnd)
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Ticket ticket = (Ticket) obj;
            return new EqualsBuilder()
                    .append(cost, ticket.cost)
                    .append(count, ticket.count)
                    .append(sessionTimeStart, ticket.getSessionTimeStart())
                    .append(sessionTimeEnd, ticket.getSessionTimeEnd())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
