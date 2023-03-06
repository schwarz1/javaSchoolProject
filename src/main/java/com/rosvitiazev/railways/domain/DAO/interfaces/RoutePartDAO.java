package com.rosvitiazev.railways.domain.DAO.interfaces;


import com.rosvitiazev.railways.domain.entities.RoutePart;


public interface RoutePartDAO extends GenDAO<RoutePart> {

    RoutePart getStationIdFromTo(int stationIdFrom, int stationIdTo);

}
