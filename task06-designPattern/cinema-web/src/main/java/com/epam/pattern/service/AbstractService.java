package com.epam.pattern.service;

import com.epam.pattern.system.DBConnectionManager;
import java.sql.Connection;

/**
 * Created by Aliaksandr_Shynkevich on 2/7/15
 */
public abstract class AbstractService {

    private DBConnectionManager manager;

    public AbstractService(DBConnectionManager connectionManager) {
        this.manager = connectionManager;
    }

    protected Connection getConnection() {
        return manager.getConnection();
    }

    protected void closeConnection(Connection connection) {
        manager.closeConnection(connection);
    }
}
