package com.epam.pattern.repository;

import com.epam.pattern.domain.StorageDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public abstract class AbstractRepository<T extends StorageDomain> {

    private static final String READ_SQL = "SELECT * FROM %s WHERE %s = ?";
    private static final String DELETE_SQL = "DELETE FROM %s WHERE %s = ?";

    private Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(T entity) throws SQLException {
        getCreateStatement(entity).executeUpdate();
    }

    public T read(String id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(String.format(READ_SQL, getTableName(), getIdColumnName()));
        preparedStatement.setString(1, id);
        return wrapObject(preparedStatement.executeQuery());
    }

    public void update(T entity) {

    }

    public void delete(String id) throws SQLException {
        connection.prepareStatement(String.format(DELETE_SQL, getIdColumnName())).setString(1, id);
    }

    public Connection getConnection() {
        return connection;
    }

    protected abstract String getTableName();

    protected abstract String getIdColumnName();

    protected abstract PreparedStatement getCreateStatement(T entity);

    protected abstract T wrapObject(ResultSet resultSet);
}
