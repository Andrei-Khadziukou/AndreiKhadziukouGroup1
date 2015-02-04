package com.epam.pattern.system;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * task06-designPattern class
 * <p/>
 * Copyright (C) 2015 copyright.com
 * <p/>
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class DBConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(DBConnectionManager.class);

    private String url;
    private String dbUser;
    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("JDBC driver initialization error: ", e);
        }
    }

    public DBConnectionManager(String url, String dbUser, String dbPassword) {
        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            LOGGER.info(String.format("Connection has been established! URL=[%s] dbUser=[%s]",
                    url, dbUser));
            this.url = url;
            this.dbUser = dbUser;
        } catch (SQLException e) {
            LOGGER.error("Connection dabase error: ", e);
            throw new RuntimeException("Database driver not exists: ", e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            LOGGER.info(String.format("Connection has been closed! URL=[%s] dbUser=[%s]",
                    url, dbUser));
        } catch (SQLException e) {
            new RuntimeException("Close connection", e);
        }
    }

}
