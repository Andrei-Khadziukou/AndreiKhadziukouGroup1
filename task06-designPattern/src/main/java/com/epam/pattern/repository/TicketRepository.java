package com.epam.pattern.repository;

import com.epam.pattern.domain.Ticket;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class TicketRepository extends AbstractRepository<Ticket> {

    private static final String READ_SQL = "SELECT t.ticket_id, t.session_name, t.ticket_cost, tpm.amount " +
            "FROM tickets t, (SELECT ticket_id, count(*) as amount FROM ticket_place_map GROUP BY ticket_id) tpm " +
            "WHERE t.ticket_id = tpm.ticket_id ORDER BY t.session_name asc";

    private static final String INSERT_SQL = "INSERT INTO tickets (session_name, ticket_cost, ticket_id) VALUES (?,?,?)";
    private static final String UPDATE_SQL = "UPDATE tickets SET session_name = ?, ticket_cost = ? where ticket_id = ?";
    private static final String SELECT_PLACES_SQL = "SELECT count(*) FROM ticket_place_map WHERE ticket_id = ? " +
            "AND place_number in (%s)";
    private static final String PLACES_BY_ID = "SELECT place_number FROM ticket_place_map WHERE ticket_id = ?";
    private static final String DELETE_PLACES_SQL = "DELETE FROM ticket_place_map WHERE ticket_id " +
            "= ? AND place_number in (%s)";

    public List<Ticket> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(String.format(READ_SQL, getTableName(), getIdColumnName()));
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()) {
            Ticket ticket = getTicket(resultSet);
            ticket.setCount(resultSet.getInt("amount"));
            tickets.add(ticket);
        }
        return tickets;
    }

    public int getCountExistsPlaces(Ticket ticket, String placeNumbers, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format(SELECT_PLACES_SQL, placeNumbers));
        statement.setString(1, ticket.getId());
        ResultSet resultSet = statement.executeQuery();
        return (resultSet.next()) ? resultSet.getInt(1) : 0;
    }

    public void sellPlaces(Ticket ticket, String placeNumbers, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format(DELETE_PLACES_SQL, placeNumbers));
        statement.setString(1, ticket.getId());
        statement.executeUpdate();
    }

    public List<Integer> getPlacesByTicket(Ticket ticket, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(PLACES_BY_ID);
        statement.setString(1, ticket.getId());
        ResultSet resultSet = statement.executeQuery();
        List<Integer> places = new ArrayList<>();
        while (resultSet.next()) {
            places.add(resultSet.getInt("place_number"));
        }
        return places;
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
    protected PreparedStatement getCreateStatement(Ticket entity, Connection connection) throws SQLException {
        return getStatement(INSERT_SQL, entity, connection);
    }

    @Override
    protected PreparedStatement getUpdateStatement(Ticket entity, Connection connection) throws SQLException {
        return getStatement(UPDATE_SQL, entity, connection);
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
        Ticket ticket = new Ticket(id, name, cost);
        return ticket;
    }

    private PreparedStatement getStatement(String sql, Ticket entity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.getName());
        statement.setBigDecimal(2, entity.getCost());
        statement.setString(3, entity.getId());
        return statement;
    }
}
