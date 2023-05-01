package com.rosvitiazev.railways.service;


import com.rosvitiazev.railways.entity.Passenger;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PassengerService {

    void updatePassenger(Passenger passenger);

    Passenger getPassengerById(Long passengerId);

    void deletePassenger(Long id);

    List<Passenger> getAllPassenger(Long trainId);

    void createPassenger(Passenger passenger);
}
