package com.epam.mentor.api;

import com.epam.mentor.domain.Deposit;
import com.epam.mentor.domain.DepositKey;

import java.util.Map;

/**
 * jboss-ejb class
 *
 * Date: Sep 11, 2014
 *
 * @author Aliaksandr_Shynkevich
 */
public interface IDepositEjb {

    public Map<DepositKey, Deposit> getDepositsByAccount(String serial);

    public void addNewDeposit(Deposit deposit);
}
