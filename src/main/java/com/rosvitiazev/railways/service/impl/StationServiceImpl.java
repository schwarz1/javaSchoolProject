package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.repository.StationRepository;
import com.rosvitiazev.railways.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public void createStation(Station station) {
        stationRepository.save(station);
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Station> getAllStations() {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(0, 1000, sort);
        Page<Station> stationRepositoryAll = stationRepository.findAll(pageable);
        return stationRepositoryAll.getContent();
    }

    @Override
    public void updateStation(Station station) {
        stationRepository.save(station);
    }

    @Override
    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }
}
