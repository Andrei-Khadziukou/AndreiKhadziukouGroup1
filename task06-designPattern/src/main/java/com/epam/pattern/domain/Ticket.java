package com.epam.pattern.domain;

import java.math.BigDecimal;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class Ticket extends StorageDomain {
    private BigDecimal cost;
    private Integer count;

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
}
