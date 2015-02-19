package com.epam.pattern.aggregator;

import com.epam.pattern.domain.Ticket;
import java.util.List;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public class TicketInfoResponse {
    private Ticket ticket;
    private List<Integer> availablePlaces;

    public TicketInfoResponse() {
        // Default ctor.
    }

    public TicketInfoResponse(Ticket ticket, List<Integer> availablePlaces) {
        this.ticket = ticket;
        this.availablePlaces = availablePlaces;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Integer> getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(List<Integer> availablePlaces) {
        this.availablePlaces = availablePlaces;
    }
}
