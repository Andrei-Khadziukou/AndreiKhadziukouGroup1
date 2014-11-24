package com.epam.mentor.user.controller;

import com.epam.mentor.api.IDepositEjbLocal;
import com.epam.mentor.api.IExchangeEjbLocal;
import com.epam.mentor.api.IUserEjbLocal;
import com.epam.mentor.domain.Account;
import com.epam.mentor.domain.Deposit;
import com.epam.mentor.domain.DepositKey;
import com.epam.mentor.domain.ExchangeRate;
import com.epam.mentor.user.validation.TransferMoneyValidator;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@RolesAllowed("user")
@WebServlet(description = "Deposit transfer servlet", urlPatterns = {"/deposits/transfer"})
public class DepositTransferServlet extends HttpServlet {

    @EJB
    private IExchangeEjbLocal exchangeRateEjb;
    @EJB
    private IUserEjbLocal userEjb;
    @EJB
    private IDepositEjbLocal depositEjb;

    private TransferMoneyValidator transferMoneyValidator;

    @PostConstruct
    private void initer() {
        transferMoneyValidator = new TransferMoneyValidator(depositEjb);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/deposits");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String srcCur = req.getParameter("srcCur");
        String destCur = req.getParameter("destCur");
        BigDecimal value = NumberUtils.createBigDecimal(req.getParameter("value"));
        List<String> errors = new ArrayList<>();

        Account account = userEjb.getAccountByUsername(req.getRemoteUser());
        Map<DepositKey, Deposit> depositMap = depositEjb.getDepositsByAccount(account.getSerialNumber());
        if (srcCur.equals(destCur)) {
            toPage(req, resp, errors);
        }
        ExchangeRate exchangeRate = exchangeRateEjb.getExchangeRate(srcCur, destCur);
        if (exchangeRate == null) {
            errors.add("You select impossible exchange. Please change your choise");
            toPage(req, resp, errors);
            return;
        }
        if (transferMoneyValidator.validate(srcCur, value, account, errors)) {
            BigDecimal newValue = value.divide(exchangeRate.getValue(), 2, BigDecimal.ROUND_HALF_UP);
            synchronized (depositMap) {
                Deposit firstPoint = depositMap.get(new DepositKey(account.getSerialNumber(), srcCur));
                Deposit secondPoint = depositMap.get(new DepositKey(account.getSerialNumber(), destCur));
                firstPoint.setValue(firstPoint.getValue().subtract(value));
                secondPoint.setValue(secondPoint.getValue().add(newValue));
            }
        }
        toPage(req, resp, errors);
    }

    private void toPage(HttpServletRequest req, HttpServletResponse resp, List<String> errors)
            throws ServletException, IOException {
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("../WEB-INF/views/deposit.jsp").forward(req, resp);
    }
}
