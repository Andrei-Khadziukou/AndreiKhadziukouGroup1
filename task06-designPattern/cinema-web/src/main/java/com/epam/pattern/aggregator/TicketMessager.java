package com.epam.pattern.aggregator;

import com.epam.pattern.core.adapter.ChannelAdapter;
import com.epam.pattern.core.adapter.ChannelAdapterFactory;
import com.epam.pattern.core.domain.PlaceStatusDomain;
import com.epam.pattern.core.domain.Ticket;
import com.epam.pattern.core.domain.TicketMessage;
import com.epam.pattern.core.domain.TicketStatusEnum;

/**
 * Created by Aliaksandr_Shynkevich on 2/22/15
 */
public class TicketMessager {
    private ChannelAdapter channelAdapter = ChannelAdapterFactory.getAdapter();

    public void informAboutTicket(Ticket ticket, String places, TicketStatusEnum status) {
        TicketMessage message = new TicketMessage();
        PlaceStatusDomain statusDomain = new PlaceStatusDomain(places, status);
        message.setMessage(String.format("Ticket(s) [session = %s] has new status", ticket.getName()));
        message.setValue(statusDomain);
        channelAdapter.sendMessage(message);
    }
}
