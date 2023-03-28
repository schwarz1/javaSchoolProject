package com.rosvitiazev.railways.services.interfaces;

import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.domain.entities.Ticket;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public interface BookService {
    public boolean checkTrainExistByTrainNumber(String number);

    public Ticket saveTicket(String number, int station_id_departure, int station_id_destination, LocalDate departure_date, Passenger passenger);

    public boolean checkVacantPlaces(String number, LocalDate departure_dateFormat);

    public boolean isPassengerAlreadyRegistered(String number, LocalDate departure_dateFormat, Passenger passenger);

    public Passenger createPassenger(String first_name, String last_name, String birth_date, String passport_number) ;


    public String payTicketProcess(String first_name,
                                   String last_name,
                                   String birth_date,
                                   String number,
                                   int station_id_departure,
                                   int station_id_destination,
                                   String departure_date,
                                   String departure_time,
                                   String passport_number,
                                   HttpServletRequest request);
}