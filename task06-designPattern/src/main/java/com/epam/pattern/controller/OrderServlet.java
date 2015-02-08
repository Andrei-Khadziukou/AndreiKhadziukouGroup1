package com.epam.pattern.controller;

import com.epam.pattern.BusinessException;
import com.epam.pattern.domain.Ticket;
import com.epam.pattern.domain.User;
import com.epam.pattern.service.TicketService;
import com.epam.pattern.system.DBConnectionManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
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
    public static final String WRONG_PLACES_INPUT_FORMAT_MSG = "Wrong places input format";
    public static final String BUY_TICKETS_ERROR_MSG = "Buy tickets error: ";
    public static final String GETTING_TICKET_ERROR_MSG = "Getting ticket error: ";

    private Pattern pattern = Pattern.compile("\\d(,\\d)*");

    private TicketService ticketService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBConnectionManager manager = DBConnectionManager.getInstance();
        ticketService = new TicketService(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Ticket ticket = ticketService.findTicket(id);
            req.setAttribute("ticket", ticket);
        } catch (SQLException e) {
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
        if (pattern.matcher(placesParam).matches()) {
            User user = (User) req.getSession().getAttribute("user");
            try {
                ticketService.buyTickets(ticketId, user, placesParam);
            } catch (BusinessException e) {
                LOGGER.error(BUY_TICKETS_ERROR_MSG, e);
                req.setAttribute("error", BUY_TICKETS_ERROR_MSG + e.getMessage());
                doGet(req, resp);
                return;
            }
        } else {
            LOGGER.warn(WRONG_PLACES_INPUT_FORMAT_MSG);
            req.setAttribute("error", WRONG_PLACES_INPUT_FORMAT_MSG);
            doGet(req, resp);
            return;
        }
        resp.sendRedirect("/cinema/home");
    }
}
