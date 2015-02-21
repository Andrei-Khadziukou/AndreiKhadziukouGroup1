package com.epam.pattern.validation;

import com.epam.pattern.BusinessException;
import com.epam.pattern.core.domain.Ticket;
import com.epam.pattern.core.domain.User;
import com.epam.pattern.service.TicketService;
import com.epam.pattern.system.DBConnectionManager;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public class TicketAntiCorruption {
    private static final Logger LOGGER = Logger.getLogger(TicketAntiCorruption.class);

    private static final String WRONG_PLACES_INPUT_FORMAT_MSG = "Wrong places input format";
    private static final String GET_TICKETS_ERROR_MSG = "Get tickets error: ";
    private static final String GET_PLACES_FOT_TICKET_ERROR_MSG = "Get places fot ticket error: ";
    private static final String FIND_TICKET_ERROR_MSG = "Find ticket error: ";

    private TicketService ticketService;
    private Pattern pattern = Pattern.compile("\\d(,\\d)*");

    public TicketAntiCorruption(DBConnectionManager connectionManager) {
        ticketService = new TicketService(connectionManager);
    }

    public Ticket findTicket(String id) throws BusinessException {
        try {
            return ticketService.findTicket(id);
        } catch (SQLException e) {
            LOGGER.error(FIND_TICKET_ERROR_MSG, e);
            throw new BusinessException(FIND_TICKET_ERROR_MSG, e);
        }
    }

    public List<Ticket> getTickets() throws BusinessException {
        try {
            return ticketService.getTickets();
        } catch (SQLException e) {
            LOGGER.error(GET_TICKETS_ERROR_MSG, e);
            throw new BusinessException(GET_TICKETS_ERROR_MSG, e);
        }
    }

    public List<Integer> getPlacesByTicket(Ticket ticket) throws BusinessException {
        try {
            return ticketService.getPlacesByTicket(ticket);
        } catch (SQLException e) {
            LOGGER.error(GET_TICKETS_ERROR_MSG, e);
            throw new BusinessException(GET_PLACES_FOT_TICKET_ERROR_MSG, e);
        }
    }

    public void buyTickets(String ticketId, User user, String places) throws BusinessException {
        if (pattern.matcher(places).matches()) {
            ticketService.buyTickets(ticketId, user, places);
        } else {
            LOGGER.warn(WRONG_PLACES_INPUT_FORMAT_MSG);
            throw new BusinessException(WRONG_PLACES_INPUT_FORMAT_MSG);
        }
    }
}
