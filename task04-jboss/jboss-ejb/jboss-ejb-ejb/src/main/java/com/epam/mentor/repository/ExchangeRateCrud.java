package com.epam.mentor.repository;

import com.epam.mentor.domain.ExchangeRate;
import com.epam.mentor.domain.ExchangeRateKey;
import com.epam.mentor.repository.util.RepositoryUtils;
import java.math.BigDecimal;
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
public class ExchangeRateCrud implements ICrud<ExchangeRateKey, ExchangeRate> {

    private Map<ExchangeRateKey, ExchangeRate> exchangeRateMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        ExchangeRateKey key = new ExchangeRateKey("EUR", "USD");
        exchangeRateMap.put(key, new ExchangeRate(key, new BigDecimal("1.256")));
        key = new ExchangeRateKey("EUR","RUB");
        exchangeRateMap.put(key, new ExchangeRate(key, new BigDecimal("57.73")));
        key = new ExchangeRateKey("USD","RUB");
        exchangeRateMap.put(key, new ExchangeRate(key, new BigDecimal("46.41")));
    }

    @Override
    public ExchangeRateKey create(ExchangeRate entity) {
        ExchangeRateKey key = entity.getId();
        exchangeRateMap.put(key, entity);
        return key;
    }

    @Override
    public ExchangeRate read(ExchangeRateKey key) {
        return exchangeRateMap.get(key);
    }

    @Override
    public void update(ExchangeRate entity) {
        exchangeRateMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(ExchangeRateKey key) {
        exchangeRateMap.get(key);
    }

    public List<ExchangeRate> getAll() {
        return RepositoryUtils.getObjectList(exchangeRateMap);
    }

}
