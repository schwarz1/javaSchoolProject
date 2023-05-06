package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.repository.PassengerRepository;
import com.rosvitiazev.railways.service.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    @Override
    public void createPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

}