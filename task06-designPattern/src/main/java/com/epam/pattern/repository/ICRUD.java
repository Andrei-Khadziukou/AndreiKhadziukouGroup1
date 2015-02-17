package com.epam.pattern.repository;

import com.epam.pattern.domain.StorageDomain;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public interface ICRUD<T extends StorageDomain> {

    public void create(T entity, Connection connection) throws SQLException;

    T read(String id, Connection connection) throws SQLException;

    void update(T entity, Connection connection) throws SQLException;

    void delete(String id, Connection connection) throws SQLException;
}
