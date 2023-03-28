package com.rosvitiazev.railways.services.impl;

import com.rosvitiazev.railways.domain.dao.interfaces.StationDAO;
import com.rosvitiazev.railways.domain.entities.Station;
import com.rosvitiazev.railways.exception.CustomSQLException;
import com.rosvitiazev.railways.services.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    private final StationDAO stationDAO;

    @Autowired
    public StationServiceImpl(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public Station addStation(String name) throws CustomSQLException {

        Station station = new Station(name);
        return stationDAO.save(station);
    }

}
