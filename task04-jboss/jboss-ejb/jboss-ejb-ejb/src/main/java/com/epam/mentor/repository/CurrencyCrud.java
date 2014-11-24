package com.epam.mentor.repository;

import com.epam.mentor.repository.util.RepositoryUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14.
 */
@Singleton
@LocalBean
public class CurrencyCrud implements ICrud<String, String>{

    private Set<String> currencyMap = Collections.synchronizedSet(new HashSet<String>());

    @PostConstruct
    private void init() {
        currencyMap.add("USD");
        currencyMap.add("EUR");
        currencyMap.add("RUB");
        currencyMap.add("BYR");
    }

    @Override
    public String create(String entity) {
        currencyMap.add(entity);
        return entity;
    }

    @Override
    public String read(String key) {
        return currencyMap.contains(key) ? key : null;
    }

    @Override
    public void update(String entity) {
        currencyMap.add(entity);
    }

    @Override
    public void delete(String key) {
        currencyMap.remove(key);
    }

    public List<String> getAll() {
        List list = new ArrayList<>();
        list.addAll(currencyMap);
        return list;
    }
}
