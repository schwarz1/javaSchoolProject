package com.rosvitiazev.railways.services.impl;

import com.rosvitiazev.railways.domain.dao.interfaces.TicketDAO;
import com.rosvitiazev.railways.domain.entities.Ticket;
import com.rosvitiazev.railways.exception.CustomSQLException;
import com.rosvitiazev.railways.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;

    @Autowired
    public TicketServiceImpl(final TicketDAO ticketDAO) {

        this.ticketDAO = ticketDAO;
    }

    public List<Ticket> getTicketByPassengerID(int id) throws CustomSQLException {

        return ticketDAO.getTicketByPassengerID(id);
    }
}
