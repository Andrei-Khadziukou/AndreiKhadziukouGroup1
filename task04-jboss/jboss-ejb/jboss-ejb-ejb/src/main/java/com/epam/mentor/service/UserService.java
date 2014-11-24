package com.epam.mentor.service;

import com.epam.mentor.domain.Account;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Created by Aliaksandr_Shynkevich on 11/23/14
 */
@LocalBean
@Singleton
public class UserService {

    private Map<String, Account> userAccountMap = Collections.synchronizedMap(new HashMap<String, Account>());

    {
        String serverPath = System.getProperty("jboss.server.config.dir");
        String propUsersPath = serverPath + "/my-users.properties";
        Properties properties = new Properties();
        Set<String> usersSet;
        try {
            properties.load(new FileInputStream(propUsersPath));
            usersSet = properties.stringPropertyNames();
        } catch (IOException e) {
            usersSet = Collections.emptySet();
        }
        for (String username : usersSet) {
            userAccountMap.put(username, null);
        }
    }

    public void assign(String userName, Account account) {
        userAccountMap.put(userName, account);
    }

    public void unassignBySerial(Account account) {
        if (userAccountMap.containsValue(account)) {
            userAccountMap.remove(account);
        }
    }

    public void unassignByUsername(String username) {
        Account account = userAccountMap.get(username);
        if (account != null) {
            userAccountMap.remove(account);
        }
    }

    public Account getAccountByUser(String userName) {
        return userAccountMap.get(userName);
    }


    public Map<String, Account> getUserAccounts() {
        return userAccountMap;
    }
}
