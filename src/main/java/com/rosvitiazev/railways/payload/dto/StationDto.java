package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.RouteSheet;
import com.rosvitiazev.railways.entity.Schedule;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StationDto {
    private Long id;

    private String name;

    private Set<RouteSheet> routeSheets = new HashSet<>();

    @OneToMany(mappedBy = "station")
    private Set<Schedule> schedules = new HashSet<>();
}