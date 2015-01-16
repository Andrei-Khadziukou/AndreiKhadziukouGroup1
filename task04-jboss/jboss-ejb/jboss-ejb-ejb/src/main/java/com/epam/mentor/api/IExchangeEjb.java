package com.epam.mentor.api;

import com.epam.mentor.domain.ExchangeRate;
import com.epam.mentor.domain.ExchangeRateKey;

import java.util.List;

/**
 * jboss-ejb class
 *
 * Date: Sep 11, 2014
 *
 * @author Aliaksandr_Shynkevich
 */
public interface IExchangeEjb {

    public void addExchangeRate(ExchangeRate rate) ;

    public void updateRate(ExchangeRate rate);

    public void removeRate(ExchangeRateKey rateKey);

    public ExchangeRate getExchangeRate(String scrCurrency, String destCurrency);

    public List<ExchangeRate> getExchangeRates();
}
