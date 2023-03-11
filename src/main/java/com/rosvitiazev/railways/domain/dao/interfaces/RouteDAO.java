package com.rosvitiazev.railways.domain.dao.interfaces;

import com.rosvitiazev.railways.domain.entities.Route;

public interface RouteDAO extends GenDAO<Route> {
    public Route getRoute(int id);

}
