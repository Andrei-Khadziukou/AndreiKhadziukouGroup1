package com.epam.pattern.aggregator;

import com.epam.pattern.domain.Ticket;
import java.util.List;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public class TicketsResponse {

    private List<Ticket> tickets;

    public TicketsResponse() {
        // Default ctor.
    }

    public TicketsResponse(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
