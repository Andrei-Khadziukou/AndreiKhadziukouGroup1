package com.epam.mentor.domain;

import java.math.BigDecimal;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14
 */
public class ExchangeRate {

    private ExchangeRateKey id;
    private BigDecimal value;

    public ExchangeRate(String srcCurrency, String destCurrency, BigDecimal value) {
        this(new ExchangeRateKey(srcCurrency, destCurrency), value);
    }

    public ExchangeRate(ExchangeRateKey id, BigDecimal value) {
        this.id = id;
        this.value = value;
    }

    public ExchangeRateKey getId() {
        return id;
    }

    public void setId(ExchangeRateKey id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
