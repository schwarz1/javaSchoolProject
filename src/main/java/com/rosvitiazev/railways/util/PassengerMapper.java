package com.rosvitiazev.railways.util;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.payload.dto.PassengerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PassengerMapper {
    private final ModelMapper modelMapper;

    public Passenger mapToEntity(PassengerDto passengerDto) {
        return modelMapper.map(passengerDto, Passenger.class);
    }

    public PassengerDto mapToDTO(Passenger newPassenger) {
        return modelMapper.map(newPassenger, PassengerDto.class);
    }
}
