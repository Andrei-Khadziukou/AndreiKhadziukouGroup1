package com.epam.mentor.repository;

import com.epam.mentor.domain.Deposit;
import com.epam.mentor.domain.DepositKey;

import java.math.BigDecimal;
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
public class DepositCrud implements ICrud<DepositKey, Deposit> {

    private Map<String, Map<DepositKey, Deposit>> accoutnDepositMap = new ConcurrentHashMap<>();
    private Map<DepositKey, Deposit> depositMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        Deposit deposit = new Deposit("BM199008", "USD", new BigDecimal("200"));
        Deposit deposit2 = new Deposit("BM199008", "EUR", new BigDecimal("1200"));
        create(deposit);
        create(deposit2);
    }

    @Override
    public DepositKey create(Deposit entity) {
        DepositKey depositKey = entity.getDepositKey();
        depositMap.put(depositKey, entity);
        String key = entity.getDepositKey().getAccountId();
        Map<DepositKey, Deposit> accDeposits = accoutnDepositMap.get(key);
        if (accDeposits == null) {
            accDeposits = new ConcurrentHashMap<>();
        }
        accDeposits.put(depositKey, entity);
        accoutnDepositMap.put(key, accDeposits);
        return depositKey;
    }

    @Override
    public Deposit read(DepositKey key) {
        return depositMap.get(key);
    }

    @Override
    public void update(Deposit entity) {
        DepositKey depositKey = entity.getDepositKey();
        Deposit deposit = depositMap.get(depositKey);
        deposit.setValue(entity.getValue());
    }

    @Override
    public void delete(DepositKey key) {
        depositMap.remove(key);
    }

    public Map<DepositKey, Deposit> getAllByAccount(String accId) {
        return accoutnDepositMap.get(accId);
    }
}
