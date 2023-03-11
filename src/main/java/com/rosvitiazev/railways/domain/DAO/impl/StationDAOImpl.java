package com.rosvitiazev.railways.domain.DAO.impl;

import com.rosvitiazev.railways.domain.DAO.interfaces.StationDAO;
import com.rosvitiazev.railways.domain.entities.Station;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("stationDAO")
public class StationDAOImpl implements StationDAO {

    @PersistenceContext
    private EntityManager manager;

    public StationDAOImpl() {
    }

    @Override
    @Transactional
    public Station save(Station entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save station: " + ex);
        }
        return entity;
    }

    @Override
    public List<Station> findAll() {
        TypedQuery<Station> query = manager.createNamedQuery("Station.GetAllStationList", Station.class);
        List<Station> result = query.getResultList();
        return result;
    }

    @Override
    public Station delete(Station entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove station: " + ex);
        }
        return entity;
    }

    @Override
    public Station update(Station entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update station: " + ex);
        }
        return entity;
    }

    @Override
    public Station findById(int id) {
        Station result = manager.find(Station.class, id);
        return result;
    }

    @Override
    public Station getStation(String name) {
        Station result;
        try {
            TypedQuery<Station> query = manager.createNamedQuery("Station.GetStationByName", Station.class);
            query.setParameter("stationName", name);
            result = query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new CustomSQLException(ex.getMessage());
        }
        return result;
    }

}