package com.epam.pattern.repository;

import com.epam.pattern.domain.Ticket;
import com.epam.pattern.system.DBConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class TicketRepository extends AbstractRepository<Ticket> {

    private static final String READ_SQL = "SELECT * FROM tickets ORDER BY session_name asc";
    private static final String INSERT_SQL = "INSERT INTO tickets (session_name, ticket_cost, ticket_id) VALUES (?,?,?)";
    private static final String UPDATE_SQL = "UPDATE tickets SET session_name = ?, ticket_cost = ? where ticket_id = ?";

    public TicketRepository(DBConnectionManager manager) {
        super(manager);
    }

    public List<Ticket> readAll() throws SQLException {
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(String.format(READ_SQL, getTableName(), getIdColumnName()));
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()) {
            tickets.add(getTicket(resultSet));
        }
        return tickets;
    }

    @Override
    protected String getTableName() {
        return "tickets";
    }

    @Override
    protected String getIdColumnName() {
        return "ticket_id";
    }

    @Override
    protected PreparedStatement getCreateStatement(Ticket entity) throws SQLException {
        return getStatement(INSERT_SQL, entity);
    }

    @Override
    protected PreparedStatement getUpdateStatement(Ticket entity) throws SQLException {
        return getStatement(UPDATE_SQL, entity);
    }

    @Override
    protected Ticket wrapObject(ResultSet resultSet) throws SQLException {
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = getTicket(resultSet);
        }
        return ticket;
    }

    private Ticket getTicket(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(getIdColumnName());
        String name = resultSet.getString("session_name");
        BigDecimal cost = resultSet.getBigDecimal("ticket_cost");
        return new Ticket(id, name, cost);
    }

    private PreparedStatement getStatement(String sql, Ticket entity) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, entity.getName());
        statement.setBigDecimal(2, entity.getCost());
        statement.setString(3, entity.getId());
        return statement;
    }
}
