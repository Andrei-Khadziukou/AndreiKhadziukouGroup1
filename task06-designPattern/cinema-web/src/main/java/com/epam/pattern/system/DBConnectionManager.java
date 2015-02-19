package com.epam.pattern.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class DBConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(DBConnectionManager.class);
    private static Queue<Connection> connectionPool = new ConcurrentLinkedQueue<>();

    private static DBConnectionManager connectionManager = new DBConnectionManager();

    public static DBConnectionManager getInstance() {
        return connectionManager;
    }

    private String url;
    private String dbUser;
    private String dbPassword;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("JDBC driver initialization error: ", e);
        }
    }

    private DBConnectionManager() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/configuration.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
            url = properties.getProperty("db.url");
        } catch (Exception e) {
            LOGGER.error("Read properties error: ", e);
            throw new RuntimeException("Read properties error: ", e);
        }
    }

    public Connection getConnection() {
        Connection connection = connectionPool.poll();
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
            connectionPool.add(connection);
    }

    private Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            LOGGER.info(String.format("Connection has been established! URL=[%s] dbUser=[%s]",
                    url, dbUser));
            return connection;
        } catch (SQLException e) {
            LOGGER.error("Connection database error: ", e);
            throw new RuntimeException("Database driver not exists: ", e);
        }
    }
}
