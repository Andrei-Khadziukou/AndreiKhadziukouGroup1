package com.epam.pattern.core.domain;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class TicketMessage implements Messageable {

    private String message;
    private PlaceStatusDomain value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public PlaceStatusDomain getValue() {
        return value;
    }

    public void setValue(PlaceStatusDomain value) {
        this.value = value;
    }
}
