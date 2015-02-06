package com.epam.pattern.service;

import com.epam.pattern.domain.Ticket;
import com.epam.pattern.repository.TicketRepository;
import com.epam.pattern.system.DBConnectionManager;

import java.sql.SQLException;
import java.util.List;

/**
 * task06-designPattern class
 * <p/>
 * Copyright (C) 2015 copyright.com
 * <p/>
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class TicketService {

    private TicketRepository repository;

    public TicketService(DBConnectionManager manager) {
        repository = new TicketRepository(manager);
    }

    public List<Ticket> getTickets() throws SQLException {
        return repository.readAll();
    }
}
