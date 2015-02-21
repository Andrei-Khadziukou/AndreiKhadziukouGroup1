package com.epam.pattern.controller;

import com.epam.pattern.BusinessException;
import com.epam.pattern.core.domain.Ticket;
import com.epam.pattern.core.domain.User;
import com.epam.pattern.system.DBConnectionManager;
import com.epam.pattern.validation.TicketAntiCorruption;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 1/17/15
 */
@WebServlet("/cinema/order")
public class OrderServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(OrderServlet.class);
    public static final String BUY_TICKETS_ERROR_MSG = "Buy tickets error: ";
    public static final String GETTING_TICKET_ERROR_MSG = "Getting ticket error: ";

    private TicketAntiCorruption ticketAntiCorruption;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBConnectionManager manager = DBConnectionManager.getInstance();
        ticketAntiCorruption = new TicketAntiCorruption(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Ticket ticket = ticketAntiCorruption.findTicket(id);
            req.setAttribute("ticket", ticket);
        } catch (BusinessException e) {
            LOGGER.error(GETTING_TICKET_ERROR_MSG, e);
            req.setAttribute("error", GETTING_TICKET_ERROR_MSG + e.getMessage());
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String placesParam = req.getParameter("places");
        String ticketId = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("user");
        try {
            ticketAntiCorruption.buyTickets(ticketId, user, placesParam);
        } catch (BusinessException e) {
            LOGGER.error(BUY_TICKETS_ERROR_MSG, e);
            req.setAttribute("error", BUY_TICKETS_ERROR_MSG + e.getMessage());
            doGet(req, resp);
            return;
        }
        resp.sendRedirect("/cinema/home");
    }
}
