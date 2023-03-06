package com.rosvitiazev.railways.domain.DAO.interfaces;

import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.domain.entities.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketDAO extends GenDAO<Ticket> {

    public int countTicketOnTrain(String trainNumber, LocalDate departure_date);
    public List<Passenger> getAllPassengersByTrain(String trainNumber, LocalDate departure_date);
    public List<Ticket> getTicketByPassengerID(int passenger_id);

}
