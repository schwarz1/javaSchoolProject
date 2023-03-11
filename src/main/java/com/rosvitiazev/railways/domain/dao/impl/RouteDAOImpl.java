package com.rosvitiazev.railways.domain.dao.impl;

import com.rosvitiazev.railways.domain.dao.interfaces.RouteDAO;
import com.rosvitiazev.railways.domain.entities.Route;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository("routeDAO")
public class RouteDAOImpl implements RouteDAO {

    @PersistenceContext
    private EntityManager manager;

    public RouteDAOImpl() {
    }

    @Override
    @Transactional
    public Route save(Route entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save route: " + ex);
        }
        return entity;
    }

    @Override
    public List<Route> findAll() {
        TypedQuery<Route> query = manager.createNamedQuery("Route.GetAllRouteList", Route.class);
        List<Route> result = query.getResultList();
        return result;
    }

    @Override
    public Route delete(Route entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove route: " + ex);
        }
        return entity;
    }

    @Override
    public Route update(Route entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update route: " + ex);
        }
        return entity;
    }

    @Override
    public Route findById(int id) {
        Route result = manager.find(Route.class, id);
        return result;
    }

    @Override
    public Route getRoute(int id) {
        Route result;
        try {
            TypedQuery<Route> query = manager.createNamedQuery("Route.GetRouteById", Route.class);
            query.setParameter("routeId", id);
            result = query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new CustomSQLException(ex.getMessage());
        }
        return result;
    }

}

