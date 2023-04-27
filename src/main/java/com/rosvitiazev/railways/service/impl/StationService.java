package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElse(null);
    }
    public List<Station> getAllStations() {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(0, 1000, sort);
        Page<Station> stationRepositoryAll =stationRepository.findAll(pageable);
        return stationRepositoryAll.getContent();
    }
    public Station updateStation(Station station) {
        return stationRepository.save(station);
    }
    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }
}
