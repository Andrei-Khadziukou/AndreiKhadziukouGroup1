package com.epam.pattern.service;

import com.epam.pattern.BusinessException;
import com.epam.pattern.domain.Ticket;
import com.epam.pattern.domain.User;
import com.epam.pattern.repository.TicketRepository;
import com.epam.pattern.repository.UserRepository;
import com.epam.pattern.system.DBConnectionManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class TicketService extends AbstractService {
    private static final Logger LOGGER = Logger.getLogger(TicketService.class);
    public static final User CINEMA_USER = new User("0");

    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    public TicketService(DBConnectionManager manager) {
        super(manager);
        ticketRepository = new TicketRepository();
        userRepository = new UserRepository();
    }

    public Ticket findTicket(String id) throws SQLException {
        Connection connection = getConnection();
        try {
            return ticketRepository.read(id, connection);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Ticket> getTickets() throws SQLException {
        Connection connection = getConnection();
        try {
            return ticketRepository.readAll(connection);
        } finally {
            closeConnection(connection);
        }
    }

    public void buyTickets(String ticketId, User user, String places) throws BusinessException {
        Connection connection = getConnection();
        try {
            Savepoint savepointFirstPhase = null;
            Savepoint savepointSecondPhase = null;
            String[] placesNum = StringUtils.split(places, ',');
            try {
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                savepointFirstPhase = connection.setSavepoint("firstPhase");

                Ticket ticket = ticketRepository.read(ticketId, connection);
                BigDecimal totalCost = ticket.getCost().multiply(new BigDecimal(placesNum.length));
                if (user.getBalance().compareTo(totalCost) > -1) {
                    withdrawal(user, totalCost, connection);
                    overcharging(CINEMA_USER, totalCost, connection);
                    savepointSecondPhase = connection.setSavepoint("secondPhase");
                } else {
                    connection.rollback(savepointFirstPhase);
                    throw new BusinessException(user.getName() + " doesn't have enough money");
                }
            } catch (SQLException e) {
                try {
                    connection.rollback(savepointFirstPhase);
                } catch (SQLException e1) {
                    throw new BusinessException("Transaction exception", e1);
                }
            }

            try {
                int count = ticketRepository.getCountExistsPlaces(ticketId, places, connection);
                if (placesNum.length != count) {
                    connection.rollback(savepointFirstPhase);
                    throw new BusinessException("It doesn't have need places");
                }
                ticketRepository.sellPlaces(ticketId, places, connection);
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback(savepointSecondPhase);
                    connection.rollback(savepointFirstPhase);
                } catch (SQLException e1) {
                    throw new BusinessException("Transaction exception", e1);
                }
            }
        } finally {
            closeConnection(connection);
        }
    }

    private void withdrawal(User user, BigDecimal totalCost, Connection connection) throws SQLException {
        user.setBalance(user.getBalance().subtract(totalCost));
        LOGGER.info(String.format("Withdrawal from user (%s) - %d cops", user.getName(), user.getBalance()));
        userRepository.updateBalance(user, connection);
        LOGGER.info(String.format("Withdrawal from user (%s) is completed", user.getName()));
    }

    private void overcharging(User user, BigDecimal totalCost, Connection connection) throws SQLException {
        user.setBalance(user.getBalance().add(totalCost));
        LOGGER.info(String.format("Overcharging from user (%s) - %d cops", user.getName(), user.getBalance()));
        userRepository.updateBalance(user, connection);
        LOGGER.info(String.format("Overcharging from user (%s) is completed", user.getName()));
    }

}
