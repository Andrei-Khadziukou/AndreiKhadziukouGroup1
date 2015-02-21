package com.epam.pattern.aggregator;

import com.epam.pattern.BusinessException;
import com.epam.pattern.core.domain.Ticket;
import com.epam.pattern.system.DBConnectionManager;
import com.epam.pattern.validation.TicketAntiCorruption;
import java.util.List;
import org.apache.log4j.Logger;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public class CinemaAggregator implements ICinemaAggregator {
    private static final Logger LOGGER = Logger.getLogger(CinemaAggregator.class);
    public static final String GET_TICKET_PLACES_INFO_ERROR_MSG = "Get ticket places info error";
    private static ICinemaAggregator instance = new CinemaAggregator();

    private static final String GET_TICKETS_ERROR_MSG = "Get tickets error";

    public static ICinemaAggregator getInstance() {
        return instance;
    }

    private TicketAntiCorruption ticketService;
    private ObjectMapper mapper = JsonFactory.create();

    private CinemaAggregator() {
        DBConnectionManager manager = DBConnectionManager.getInstance();
        this.ticketService = new TicketAntiCorruption(manager);
    }

    @Override
    public String getTicketsJson() {
        try {
            List<Ticket> tickets = ticketService.getTickets();
            return mapper.toJson(tickets);
        } catch (BusinessException e) {
            LOGGER.error(GET_TICKETS_ERROR_MSG, e);
            return getErrorJson(GET_TICKETS_ERROR_MSG, e.getMessage());
        }
    }

    @Override
    public String getTicketPlacesJson(String ticketId) {
        try {
            Ticket ticket = ticketService.findTicket(ticketId);
            if (ticket == null) {
                return getErrorJson(GET_TICKET_PLACES_INFO_ERROR_MSG,
                        String.format("The ticket with id [%s] has not found", ticketId));
            }
            List<Integer> places = ticketService.getPlacesByTicket(ticket);
            TicketInfoResponse infoResponse = new TicketInfoResponse(ticket, places);
            return mapper.toJson(infoResponse);
        } catch (BusinessException e) {
            LOGGER.error(GET_TICKET_PLACES_INFO_ERROR_MSG, e);
            return getErrorJson(GET_TICKET_PLACES_INFO_ERROR_MSG, e.getMessage());
        }
    }

    @Override
    public String getErrorJson(String message, String cause) {
        return mapper.toJson(new JsonErrorResponse(message, cause));
    }


}