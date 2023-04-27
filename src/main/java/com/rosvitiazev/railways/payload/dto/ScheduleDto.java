package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.entity.Train;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDto {

    private Long id;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private Station station;
    private Train train;
}
