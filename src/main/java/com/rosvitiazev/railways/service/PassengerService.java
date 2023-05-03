package com.rosvitiazev.railways.service;


import com.rosvitiazev.railways.entity.Passenger;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PassengerService {

    void createPassenger(Passenger passenger);
}
