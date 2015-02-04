package com.epam.pattern.controller;

import com.epam.pattern.system.DBConnectionManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * task06-designPattern class
 * <p/>
 * Copyright (C) 2015 copyright.com
 * <p/>
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext config = servletContextEvent.getServletContext();

        String url = config.getInitParameter("dbURL");
        String dbUser = config.getInitParameter("dbUser");
        String dbPassword = config.getInitParameter("dbUserPwd");

        DBConnectionManager dbManager = new DBConnectionManager(url, dbUser, dbPassword);
        config.setAttribute("DBManager", dbManager);
        LOGGER.info("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        dbManager.closeConnection();
        System.out.println("Database connection closed for Application.");
    }
}
