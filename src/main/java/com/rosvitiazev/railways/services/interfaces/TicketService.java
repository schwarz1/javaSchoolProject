package com.rosvitiazev.railways.services.interfaces;

import com.rosvitiazev.railways.domain.entities.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> getTicketByPassengerID(int Id);
}
