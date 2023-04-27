package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.RouteSheet;
import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Ticket;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TrainDto {
    private Long id;
    private String number;
    private Integer seatsNumber;
    private RouteSheet routeSheet;
    private Set<Schedule> schedules = new HashSet<>();
    private Set<Ticket> tickets = new HashSet<>();
}

