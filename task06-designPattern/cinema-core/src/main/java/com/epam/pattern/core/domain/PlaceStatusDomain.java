package com.epam.pattern.core.domain;

/**
 * Created by Aliaksandr_Shynkevich on 2/22/15
 */
public class PlaceStatusDomain {
    private String places;
    private TicketStatusEnum status;

    public PlaceStatusDomain(String places, TicketStatusEnum status) {
        this.places = places;
        this.status = status;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public TicketStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TicketStatusEnum status) {
        this.status = status;
    }
}
