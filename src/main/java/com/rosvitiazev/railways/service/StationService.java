package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.entity.Station;

import java.util.List;

public interface StationService {
    void createStation(Station station);

    Station getStationById(Long id);

    List<Station> getAllStations();


    void updateStation(Station station);

    void deleteStation(Long id);


}
