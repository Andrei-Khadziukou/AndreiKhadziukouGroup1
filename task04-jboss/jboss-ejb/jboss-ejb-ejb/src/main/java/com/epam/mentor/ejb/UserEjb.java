package com.epam.mentor.ejb;

import com.epam.mentor.api.IUserEjbLocal;
import com.epam.mentor.api.IUserEjbRemote;
import com.epam.mentor.domain.Account;
import com.epam.mentor.repository.AccountCrud;
import com.epam.mentor.service.UserService;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Aliaksandr_Shynkevich on 11/23/14
 */
@Stateless
public class UserEjb implements IUserEjbLocal, IUserEjbRemote {

    @EJB
    private UserService userService;
    @EJB
    private AccountCrud accountCrud;

    @PostConstruct
    private void init() {
        assignUserToAccount("user", "BM199008");
    }

    public Map<String, Account> getUserAccounts() {
        return userService.getUserAccounts();
    }

    public void assignUserToAccount(String userName, String serial) {
        Account account = accountCrud.read(serial);
        userService.assign(userName, account);
    }

    public void unnasignUser(String userName) {
        userService.unassignByUsername(userName);
    }

    public Account getAccountByUsername(String username) {
        return userService.getAccountByUser(username);
    }
}
