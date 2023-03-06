package com.rosvitiazev.railways.domain.DAO.interfaces;

import com.rosvitiazev.railways.domain.entities.Route;

public interface RouteDAO extends GenDAO<Route> {
    public Route getRoute(int id);

}
