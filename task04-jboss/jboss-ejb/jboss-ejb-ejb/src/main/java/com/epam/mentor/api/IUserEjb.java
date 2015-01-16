package com.epam.mentor.api;

import com.epam.mentor.domain.Account;

import java.util.Map;

/**
 * jboss-ejb class
 *
 * Date: Sep 11, 2014
 *
 * @author Aliaksandr_Shynkevich
 */
public interface IUserEjb {

    public Map<String, Account> getUserAccounts();

    public void assignUserToAccount(String userName, String serial);

    public void unnasignUser(String userName);

    public Account getAccountByUsername(String username);
}
