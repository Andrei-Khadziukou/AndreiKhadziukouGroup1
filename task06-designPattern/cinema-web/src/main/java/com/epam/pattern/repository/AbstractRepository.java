package com.epam.pattern.repository;

import com.epam.pattern.core.domain.StorageDomain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public abstract class AbstractRepository<T extends StorageDomain> implements ICRUD<T> {

    private static final String READ_SQL = "SELECT * FROM %s WHERE %s = ?";
    private static final String DELETE_SQL = "DELETE FROM %s WHERE %s = ?";

    public void create(T entity, Connection connection) throws SQLException {
        getCreateStatement(entity, connection).executeUpdate();
    }

    public T read(String id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(String.format(READ_SQL, getTableName(), getIdColumnName()));
        preparedStatement.setString(1, id);
        return wrapObject(preparedStatement.executeQuery());
    }

    public void update(T entity, Connection connection) throws SQLException {
        getUpdateStatement(entity, connection).executeUpdate();
    }

    public void delete(String id, Connection connection) throws SQLException {
        connection.prepareStatement(String.format(DELETE_SQL, getIdColumnName())).setString(1, id);
    }

    protected abstract String getTableName();

    protected abstract String getIdColumnName();

    protected abstract PreparedStatement getCreateStatement(T entity, Connection connection) throws SQLException;

    protected abstract PreparedStatement getUpdateStatement(T entity, Connection connection) throws SQLException;

    protected abstract T wrapObject(ResultSet resultSet) throws SQLException;
}
