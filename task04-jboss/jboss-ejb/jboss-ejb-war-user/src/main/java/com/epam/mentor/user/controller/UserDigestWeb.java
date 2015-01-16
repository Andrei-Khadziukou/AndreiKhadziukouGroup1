package com.epam.mentor.user.controller;

import com.epam.mentor.api.IAccountEjbLocal;
import com.epam.mentor.api.ICurrencyEjbLocal;
import com.epam.mentor.api.IExchangeEjbLocal;
import com.epam.mentor.domain.Account;
import com.epam.mentor.domain.ExchangeRate;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@Named("digest")
public class UserDigestWeb {

    @EJB
    private ICurrencyEjbLocal currencyEjb;
    @EJB
    private IAccountEjbLocal accountEjb;
    @EJB
    private IExchangeEjbLocal exchangeRate;

    public List<String> getCurrencies() {
        return currencyEjb.getCurrencyList();
    }

    public List<Account> getAccounts() {
        return accountEjb.getAccountList();
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRate.getExchangeRates();
    }

}