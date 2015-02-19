package com.epam.pattern.aggregator;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public interface ICinemaAggregator {

    String getTicketsJson();

    String getTicketPlacesJson(String ticketId);

    String getErrorJson(String message, String cause);
}
