package com.epam.mentor.domain;

import com.epam.mentor.exception.InvalidKeyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14
 */
public class ExchangeRateKey {

    private String srcCurrency;
    private String destCurrency;

    public ExchangeRateKey(String srcCurrency, String destCurrency) {
        if (StringUtils.equals(srcCurrency, destCurrency)) {
            throw new InvalidKeyException("Currency shouldn't be equals");
        }
        this.srcCurrency = srcCurrency;
        this.destCurrency = destCurrency;
    }

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public String getDestCurrency() {
        return destCurrency;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(srcCurrency)
                .append(destCurrency)
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
        ExchangeRateKey key = (ExchangeRateKey) obj;
        return new EqualsBuilder()
                .append(srcCurrency, key.srcCurrency)
                .append(destCurrency, key.destCurrency)
                .isEquals();
    }

    @Override
    public String toString() {
        return srcCurrency + " -> " + destCurrency;
    }
}
