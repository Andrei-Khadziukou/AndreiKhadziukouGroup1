package com.epam.mentor.api;

import com.epam.mentor.domain.Account;

import java.util.List;

/**
 * @author Aliaksandr_Shynkevich
 */
public interface IAccountEjb {

    public List<Account> getAccountList();

    public void addAccount(Account account);

    public void removeCurrency(String accId);
}
