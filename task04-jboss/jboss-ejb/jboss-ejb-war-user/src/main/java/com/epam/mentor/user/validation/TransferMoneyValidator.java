package com.epam.mentor.user.validation;

import com.epam.mentor.api.IDepositEjb;
import com.epam.mentor.domain.Account;
import com.epam.mentor.domain.Deposit;
import com.epam.mentor.domain.DepositKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Aliaksandr_Shynkevich on 11/23/14
 */
public class TransferMoneyValidator {

    private IDepositEjb depositEjb;

    public TransferMoneyValidator(IDepositEjb depositEjb) {
        this.depositEjb = depositEjb;
    }

    public boolean validate(String srcCur, BigDecimal val, Account account, List<String> errors) {
        Map<DepositKey, Deposit> depositMap = depositEjb.getDepositsByAccount(account.getSerialNumber());
        Deposit deposit = depositMap.get(new DepositKey(account.getSerialNumber(), srcCur));
        if (deposit.getValue().compareTo(val) < 0) {
            errors.add("You don't have enough cash in your deposit");
            return false;
        }
        return true;
    }
}
