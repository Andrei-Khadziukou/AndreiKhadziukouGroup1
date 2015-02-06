package com.epam.pattern.controller;

import com.epam.pattern.domain.Ticket;
import com.epam.pattern.service.TicketService;
import com.epam.pattern.system.DBConnectionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 1/17/15
 */
@WebServlet("/home")
public class PublisherServlet extends HttpServlet {

    private TicketService ticketService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBConnectionManager dbManager = (DBConnectionManager) config.getServletContext().getAttribute("DBManager");
        ticketService = new TicketService(dbManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Ticket> tickets = ticketService.getTickets();
        } catch (SQLException e) {
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
