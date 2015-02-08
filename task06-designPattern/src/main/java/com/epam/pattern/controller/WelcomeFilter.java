package com.epam.pattern.controller;

import com.epam.pattern.BusinessException;
import com.epam.pattern.domain.User;
import com.epam.pattern.service.UserService;
import com.epam.pattern.system.DBConnectionManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/8/15
 */
@WebFilter(filterName = "filter1", urlPatterns = "/cinema/*")
public class WelcomeFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(WelcomeFilter.class);

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = new UserService(DBConnectionManager.getInstance());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        Object user = session.getAttribute("user");
        String username = request.getParameter("userName");
        if (user == null && StringUtils.isEmpty(username)) {
           introRedirect(response);
           return;
        }
        if (user != null) {
            try {
                User userUp = userService.findById(((User)user).getId());
                session.setAttribute("user", userUp);
            } catch (BusinessException e) {
                LOGGER.error(e);
                introRedirect(response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void introRedirect(ServletResponse response) throws IOException {
        ((HttpServletResponse) response).sendRedirect("/intro");
    }

    @Override
    public void destroy() {
    }
}
