package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.entity.Train;
import lombok.Data;

@Data
public class TicketDto {
    private Long id;
    private Double price;
    private Integer seatNumber;
    private Station startStation;
    private Station finishStation;
    private Train train;
    private Schedule schedule;


    private Passenger passenger;
}