package com.epam.mentor.admin.controller;

import com.epam.mentor.api.IAccountEjbLocal;
import com.epam.mentor.api.ICurrencyEjbLocal;
import com.epam.mentor.api.IExchangeEjbLocal;
import com.epam.mentor.api.IUserEjbLocal;
import com.epam.mentor.domain.Account;
import com.epam.mentor.domain.ExchangeRate;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@Named("digestAdmin")
@RequestScoped
public class AdminDigestWeb {

    @EJB
    private ICurrencyEjbLocal currencyEjb;
    @EJB
    private IAccountEjbLocal accountEjb;
    @EJB
    private IExchangeEjbLocal exchangeRate;
    @EJB
    private IUserEjbLocal userEjb;

    public List<String> getCurrencies() {
        return currencyEjb.getCurrencyList();
    }

    public List<Account> getAccounts() {
        return accountEjb.getAccountList();
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRate.getExchangeRates();
    }

    public Map<String, Account> getUserAccounts() {
        return userEjb.getUserAccounts();
    }
}