package com.epam.pattern.aggregator;

import com.epam.pattern.aggregator.util.UtilServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
@WebServlet("/rest/tickets/get")
public class GetTicketsRest extends HttpServlet {

    private ICinemaAggregator cinemaAggregator = CinemaAggregator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String ticketsResponse = cinemaAggregator.getTicketsJson();
        UtilServletResponse.printJson(ticketsResponse, response);
    }
}
