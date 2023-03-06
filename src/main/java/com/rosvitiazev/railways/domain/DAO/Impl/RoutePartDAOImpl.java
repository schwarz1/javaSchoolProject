package com.rosvitiazev.railways.domain.DAO.Impl;

import com.rosvitiazev.railways.domain.DAO.interfaces.RoutePartDAO;
import com.rosvitiazev.railways.domain.entities.RoutePart;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("routePartDAO")
public class RoutePartDAOImpl implements RoutePartDAO {

    @PersistenceContext
    private EntityManager manager;

    public RoutePartDAOImpl() {
    }

    @Override
    @Transactional
    public RoutePart save(RoutePart entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save route: " + ex);
        }
        return entity;
    }

    @Override
    public List<RoutePart> findAll() {
        TypedQuery<RoutePart> query = manager.createNamedQuery("Route.GetAllRoutePartList", RoutePart.class);
        List<RoutePart> result = query.getResultList();
        return result;
    }

    @Override
    public RoutePart delete(RoutePart entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove routePart: " + ex);
        }
        return entity;
    }

    @Override
    public RoutePart update(RoutePart entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update routePart: " + ex);
        }
        return entity;
    }

    @Override
    public RoutePart findById(int id) {
        RoutePart result = manager.find(RoutePart.class, id);
        return result;
    }

    @Override
    public RoutePart getStationIdFromTo(int stationIdFrom, int stationIdTo) {
        RoutePart result;
        try {
            TypedQuery<RoutePart> query = manager.createNamedQuery("Route.GetStationIdFromTo", RoutePart.class);
            query.setParameter("stationIdFrom", stationIdFrom);
            query.setParameter("stationIdTo", stationIdTo);
            result = query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new CustomSQLException(ex.getMessage());
        }
        return result;
    }

}
