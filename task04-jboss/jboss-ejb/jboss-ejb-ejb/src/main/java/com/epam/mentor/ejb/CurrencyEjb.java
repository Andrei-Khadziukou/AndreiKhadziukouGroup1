package com.epam.mentor.ejb;

import com.epam.mentor.api.ICurrencyEjbLocal;
import com.epam.mentor.api.ICurrencyEjbRemote;
import com.epam.mentor.repository.CurrencyCrud;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@Stateless
public class CurrencyEjb implements ICurrencyEjbLocal, ICurrencyEjbRemote {

    @EJB
    private CurrencyCrud currencyCrud;

    public List<String> getCurrencyList() {
        return currencyCrud.getAll();
    }

    public void addCurrency(String currency) {
        currencyCrud.create(currency);
    }

    public void removeCurrency(String currency) {
        currencyCrud.delete(currency);
    }

}
