package com.epam.mentor.ejb;

import com.epam.mentor.api.IDepositEjbLocal;
import com.epam.mentor.api.IDepositEjbRemote;
import com.epam.mentor.domain.Deposit;
import com.epam.mentor.domain.DepositKey;
import com.epam.mentor.repository.DepositCrud;

import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Aliaksandr_Shynkevich on 11/23/14
 */
@Stateless
public class DepositEjb implements IDepositEjbLocal, IDepositEjbRemote {

    @EJB
    private DepositCrud depositCrud;

    public Map<DepositKey, Deposit> getDepositsByAccount(String serial) {
        return depositCrud.getAllByAccount(serial);
    }

    public void addNewDeposit(Deposit deposit) {
        depositCrud.create(deposit);
    }
}
