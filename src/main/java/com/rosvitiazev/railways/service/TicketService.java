package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.entity.Ticket;

import java.util.List;

public interface TicketService {
    void createTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void deleteTicket(Long id);

    Ticket getTicketById(Long id);

    List<Ticket> getFreeSeatsOnTheTrain(Long trainId);

    void sellTicket(Ticket ticket, Passenger passenger);

    boolean existsByPassengerTrainId(String firstName,
                                     String lastName, Long trainId);

    boolean existsByPassengerPassportAndTrainId(String passportNumber,
                                                Long trainId);
}