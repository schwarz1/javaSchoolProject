package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.entity.Train;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RouteSheetDto {
    private Long id;

    private String name;

    private Set<Station> stations = new HashSet<>();

    private Set<Train> trains = new HashSet<>();
}
