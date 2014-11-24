package com.epam.mentor.repository;

import com.epam.mentor.domain.Account;
import com.epam.mentor.repository.util.RepositoryUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14
 */
@LocalBean
@Singleton
public class AccountCrud implements ICrud<String, Account> {

    private Map<String, Account> accountMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        String serial = "BM199009";
        accountMap.put(serial, new Account(serial, "James Bond", 45));
        serial = "BM199008";
        accountMap.put(serial, new Account(serial, "Elvis Presley", 35));
        serial = "BM199007";
        accountMap.put(serial, new Account(serial, "Robert Plant", 33));
        serial = "BM199006";
        accountMap.put(serial, new Account(serial, "John Lord", 53));
    }

    @Override
    public String create(Account entity) {
        String key = entity.getSerialNumber();
        accountMap.put(key, entity);
        return key;
    }

    @Override
    public Account read(String key) {
        return accountMap.get(key);
    }

    @Override
    public void update(Account entity) {
        accountMap.put(entity.getSerialNumber(), entity);
    }

    @Override
    public void delete(String key) {
        accountMap.remove(key);
    }

    public List<Account> gatAll() {
        return RepositoryUtils.getObjectList(accountMap);
    }
}
