package com.epam.pattern.controller;

import com.epam.pattern.BusinessException;
import com.epam.pattern.core.domain.Ticket;
import com.epam.pattern.core.domain.User;
import com.epam.pattern.service.UserService;
import com.epam.pattern.system.DBConnectionManager;
import com.epam.pattern.validation.TicketAntiCorruption;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet("/cinema/home")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class);
    public static final String GET_USER_ERROR_MSG = "Get user error: ";
    public static final String GET_TICKETS_ERROR_MSG = "Get tickets error: ";

    private TicketAntiCorruption ticketService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBConnectionManager manager = DBConnectionManager.getInstance();
        ticketService = new TicketAntiCorruption(manager);
        userService = new UserService(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Ticket> tickets = ticketService.getTickets();
            req.setAttribute("tickets", tickets);
        } catch (BusinessException e) {
            LOGGER.error(GET_TICKETS_ERROR_MSG, e);
            req.setAttribute("error", GET_USER_ERROR_MSG + e.getMessage());
        }
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userName = req.getParameter("userName");
            User user = userService.getUserByName(userName);
            req.getSession().setAttribute("user", user);
        } catch (SQLException e) {
            LOGGER.error(GET_USER_ERROR_MSG, e);
            req.setAttribute("error", GET_USER_ERROR_MSG + e.getMessage());
            req.getRequestDispatcher("/intro.jsp").forward(req, resp);
            return;
        }
        doGet(req, resp);
    }
}
