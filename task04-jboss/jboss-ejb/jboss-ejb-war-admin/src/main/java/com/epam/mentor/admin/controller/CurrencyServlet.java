package com.epam.mentor.admin.controller;

import com.epam.mentor.api.ICurrencyEjbLocal;
import com.epam.mentor.validation.RegExpValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@WebServlet(description = "Currency servlet", urlPatterns = {"/currency"})
public class CurrencyServlet extends HttpServlet {

    private RegExpValidator validator = new RegExpValidator("[A-Z]{3}");

    @EJB
    private ICurrencyEjbLocal currencyEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currency = req.getParameter("currency");
        List<String> errors = createErrorList();
        if (validator.validate(currency, "Currency", errors)) {
            currencyEjb.addCurrency(currency);
        }
        req.setAttribute("errors", errors);
        toPage(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currency = req.getParameter("currency");
        currencyEjb.removeCurrency(currency);
        toPage(req, resp);
    }

    private void toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/currency.jsp").forward(req,resp);
    }

    private List<String> createErrorList() {
        return new ArrayList<>();
    }
}
