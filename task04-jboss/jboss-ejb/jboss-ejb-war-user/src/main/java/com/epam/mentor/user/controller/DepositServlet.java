package com.epam.mentor.user.controller;

import com.epam.mentor.api.IDepositEjbLocal;
import com.epam.mentor.api.IUserEjbLocal;
import com.epam.mentor.domain.Account;
import com.epam.mentor.domain.Deposit;
import com.epam.mentor.domain.DepositKey;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@WebServlet(description = "Currency servlet", urlPatterns = {"/deposits"})
public class DepositServlet extends HttpServlet {

    public static final String ACCOUNT_ATTR_NAME = "account";
    public static final String DEPOSITS_ATTR_NAME = "deposits";
    @EJB
    private IUserEjbLocal userEjb;
    @EJB
    private IDepositEjbLocal depositEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toPage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newCur = req.getParameter("deposit");
        if (newCur != null) {
            Account account = userEjb.getAccountByUsername(req.getRemoteUser());
            depositEjb.addNewDeposit(new Deposit(account.getSerialNumber(), newCur, BigDecimal.ZERO));
        }
        toPage(req, resp);
    }

    private void toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = userEjb.getAccountByUsername(req.getRemoteUser());
        Map<DepositKey, Deposit> depositMap = depositEjb.getDepositsByAccount(account.getSerialNumber());
        if (depositMap != null) {
            req.getSession().setAttribute(DEPOSITS_ATTR_NAME, depositMap);
            req.getSession().setAttribute(ACCOUNT_ATTR_NAME, account);
            req.getRequestDispatcher("WEB-INF/views/deposit.jsp").forward(req,resp);
            return;
        }
        req.getRequestDispatcher("WEB-INF/views/not-account.jsp").forward(req,resp);
    }
}
