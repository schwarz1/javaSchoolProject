package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.payload.dto.PassengerDto;
import com.rosvitiazev.railways.repository.PassengerRepository;
import com.rosvitiazev.railways.service.PassengerService;
import com.rosvitiazev.railways.util.PassengerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;


    @Override
    public String updatePassenger(PassengerDto passengerDto, Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() ->
                new ResourceNotFoundException("Passenger", "id",passengerId ));
        passengerDto.setId(passenger.getId());
        passengerDto.setTicket(passenger.getTicket());
        passenger=passengerMapper.mapToEntity(passengerDto);
        passengerRepository.save(passenger);
        return passenger.getFirstName()+" Passenger updated successfully";
    }

    @Override
    public PassengerDto getPassengerById(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() ->
                new ResourceNotFoundException("Passenger", "id",passengerId ));
        return passengerMapper.mapToDTO(passenger);
    }

    @Override
    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Passenger", "id",id ));
        passengerRepository.delete(passenger);
    }

    @Override
    public List<PassengerDto> getAllPassenger(Long trainId, String sortBy, String sortDir) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 100, sort);
        Page<Passenger> passengers = passengerRepository.findAll(pageable);
        List<Passenger> passengerList = passengers.getContent();
        return passengerList.stream()
                .map(passengerMapper::mapToDTO)
                .collect(Collectors.toList());
    }


}

