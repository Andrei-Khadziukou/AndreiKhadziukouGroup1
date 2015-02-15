package com.epam.pattern.aggregator;

import com.epam.pattern.aggregator.util.UtilServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
@WebServlet("/rest/ticket/get")
public class GetTicketInfoRest extends HttpServlet {

    public static final String GET_TICKET_INFO_ERROR_MSG = "Get ticket info error";
    public static final String GET_TICKET_INFO_CAUSE_MSG = "Ticket id should not be empty";
    private ICinemaAggregator cinemaAggregator = CinemaAggregator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String ticketId = req.getParameter("id");
        if (StringUtils.isBlank(ticketId)) {
            String errorResponse = cinemaAggregator.getErrorJson(GET_TICKET_INFO_ERROR_MSG, GET_TICKET_INFO_CAUSE_MSG);
            UtilServletResponse.printJson(errorResponse, response);
            return;
        }
        String ticketsResponse = cinemaAggregator.getTicketPlacesJson(ticketId);
        UtilServletResponse.printJson(ticketsResponse, response);
    }
}
