package com.epam.pattern.repository;

import com.epam.pattern.domain.StorageDomain;
import com.epam.pattern.system.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * task06-designPattern class
 * <p/>
 * Copyright (C) 2015 copyright.com
 * <p/>
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public abstract class AbstractRepository<T extends StorageDomain> {

    private static final String READ_SQL = "SELECT * FROM %s WHERE %s = ?";
    private static final String DELETE_SQL = "DELETE FROM %s WHERE %s = ?";

    private DBConnectionManager connectionManager;

    public AbstractRepository(DBConnectionManager manager) {
        this.connectionManager = manager;
    }

    public void create(T entity) throws SQLException {
        getCreateStatement(entity).executeUpdate();
    }

    public T read(String id) throws SQLException {
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(String.format(READ_SQL, getTableName(), getIdColumnName()));
        preparedStatement.setString(1, id);
        return wrapObject(preparedStatement.executeQuery());
    }

    public void update(T entity) throws SQLException {
        getUpdateStatement(entity).executeUpdate();
    }

    public void delete(String id) throws SQLException {
        getConnection().prepareStatement(String.format(DELETE_SQL, getIdColumnName())).setString(1, id);
    }

    public Connection getConnection() {
        return connectionManager.getConnection();
    }

    protected abstract String getTableName();

    protected abstract String getIdColumnName();

    protected abstract PreparedStatement getCreateStatement(T entity) throws SQLException;

    protected abstract PreparedStatement getUpdateStatement(T entity) throws SQLException;

    protected abstract T wrapObject(ResultSet resultSet) throws SQLException;
}
