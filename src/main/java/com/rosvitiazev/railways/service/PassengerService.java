package com.rosvitiazev.railways.service;


import com.rosvitiazev.railways.payload.dto.PassengerDto;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PassengerService {

    String updatePassenger(PassengerDto passengerDto, Long passengerId);
    PassengerDto getPassengerById(Long passengerId);
    void deletePassenger(Long id);
    List<PassengerDto> getAllPassenger(Long trainId, String sortBy, String sortDir);
}
