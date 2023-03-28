package com.rosvitiazev.railways.services.interfaces;

import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.domain.entities.Train;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface PassengerService {
    public Passenger getPassengerById(int id);

    public List<Passenger> getAllPassengers(String number, LocalDate departure_date);

    public List<Train> getAllTrains();

    void createPassenger(int id, String firstName, String lastName, String email, String passportNumber);
}
