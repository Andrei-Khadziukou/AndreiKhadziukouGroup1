package com.epam.mentor.ejb;

import com.epam.mentor.api.IAccountEjbLocal;
import com.epam.mentor.api.IExchangeEjbRemote;
import com.epam.mentor.domain.Account;
import com.epam.mentor.repository.AccountCrud;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@Stateless
public class AccountEjb implements IAccountEjbLocal, IExchangeEjbRemote {
    @EJB
    private AccountCrud accountCrud;

    public List<Account> getAccountList() {
        return accountCrud.gatAll();
    }

    public void addAccount(Account account) {
        accountCrud.create(account);
    }

    public void removeCurrency(String accId) {
        accountCrud.delete(accId);
    }
}
