package com.epam.mentor.api;

import java.util.List;

/**
 * @author Aliaksandr_Shynkevich
 */
public interface ICurrencyEjb {

    public List<String> getCurrencyList();

    public void addCurrency(String currency);

    public void removeCurrency(String currency);
}
