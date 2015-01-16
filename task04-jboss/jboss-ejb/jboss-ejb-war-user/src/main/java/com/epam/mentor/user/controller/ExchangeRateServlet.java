package com.epam.mentor.user.controller;

import com.epam.mentor.api.IExchangeEjbLocal;

import java.io.IOException;
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

    private void toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/exchanges.jsp").forward(req,resp);
    }
}
