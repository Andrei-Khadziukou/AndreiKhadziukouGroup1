package com.epam.pattern.controller;

import com.epam.pattern.core.domain.User;
import com.epam.pattern.service.UserService;
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
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 1/17/15
 */
@WebServlet("/intro")
public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserServlet.class);
    public static final String GET_USERS_ERROR_MSG = "Get users error: ";

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserService(DBConnectionManager.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userService.getUsers();
            req.setAttribute("users", users);
        } catch (SQLException e) {
            LOGGER.error(GET_USERS_ERROR_MSG, e);
            req.setAttribute("error", GET_USERS_ERROR_MSG + e.getMessage());
        }
        req.getRequestDispatcher("intro.jsp").forward(req, resp);
    }
}
