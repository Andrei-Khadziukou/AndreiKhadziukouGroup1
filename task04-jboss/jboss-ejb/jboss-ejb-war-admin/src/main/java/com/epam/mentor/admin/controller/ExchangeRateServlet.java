package com.epam.mentor.admin.controller;

import com.epam.mentor.api.IExchangeEjbLocal;
import com.epam.mentor.domain.ExchangeRate;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@WebServlet(description = "Exchange servlet", urlPatterns = {"/exchanges"})
public class ExchangeRateServlet extends HttpServlet {

    @EJB
    private IExchangeEjbLocal exchangeRateEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scrCur = req.getParameter("srcCurrency");
        String destCur = req.getParameter("destCurrency");
        BigDecimal value = NumberUtils.createBigDecimal(req.getParameter("curValue"));
        exchangeRateEjb.addExchangeRate(new ExchangeRate(scrCur, destCur, value));
        toPage(req, resp);
    }

    private void toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/exchanges.jsp").forward(req,resp);
    }
}
