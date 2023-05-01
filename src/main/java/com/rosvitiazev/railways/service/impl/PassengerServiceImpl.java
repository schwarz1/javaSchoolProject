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
@Log4j
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;


    @Override
    public void createPassenger(Passenger passenger) {
        passengerRepository.save(passenger);

    }

    @Override
    public void updatePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(()
                -> new ResourceNotFoundException("Passenger", "id", passengerId));
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.delete(getPassengerById(id));
    }

    @Override
    public List<Passenger> getAllPassenger(Long trainId) {
        return passengerRepository.findAllByTicketTrainId(trainId);
    }


}
