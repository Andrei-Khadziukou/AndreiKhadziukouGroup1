package com.epam.mentor.ejb;

import com.epam.mentor.api.IExchangeEjbLocal;
import com.epam.mentor.api.IExchangeEjbRemote;
import com.epam.mentor.domain.ExchangeRate;
import com.epam.mentor.domain.ExchangeRateKey;
import com.epam.mentor.repository.ExchangeRateCrud;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * Created by Aliaksandr_Shynkevich on 11/22/14
 */
@Stateless
public class ExchangeRateEjb implements IExchangeEjbLocal, IExchangeEjbRemote {

    @EJB
    private ExchangeRateCrud exchangeRateCrud;

    public void addExchangeRate(ExchangeRate rate) {
        exchangeRateCrud.create(rate);
    }

    public void updateRate(ExchangeRate rate) {
        exchangeRateCrud.update(rate);
    }

    public void removeRate(ExchangeRateKey rateKey) {
        exchangeRateCrud.delete(rateKey);
    }

    public ExchangeRate getExchangeRate(String scrCurrency, String destCurrency) {
        return exchangeRateCrud.read(new ExchangeRateKey(scrCurrency, destCurrency));
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRateCrud.getAll();
    }
}
