package com.rosvitiazev.railways.domain.DAO.interfaces;

import com.rosvitiazev.railways.domain.entities.Station;

public interface StationDAO extends GenDAO<Station> {

    public Station getStation(String name);

}
