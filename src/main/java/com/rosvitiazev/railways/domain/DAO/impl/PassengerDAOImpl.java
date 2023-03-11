package com.rosvitiazev.railways.domain.DAO.impl;


import com.rosvitiazev.railways.domain.DAO.interfaces.PassengerDAO;
import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("passengerDAO")
public class PassengerDAOImpl implements PassengerDAO {

    @PersistenceContext
    private EntityManager manager;

    public PassengerDAOImpl() {
    }

    @Transactional
    @Override
    public Passenger save(Passenger passenger) {
        try {
            manager.persist(passenger);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save passenger: " + ex);

        }
        return passenger;
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> result = null;
        TypedQuery<Passenger> query = manager.createNamedQuery("Passenger.findAll", Passenger.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public Passenger delete(Passenger entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove passenger: " + ex);
        }
        return entity;
    }

    @Override
    public Passenger update(Passenger entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update passenger: " + ex);
        }
        return entity;
    }

    @Override
    public Passenger findById(int id) {
        Passenger result = manager.find(Passenger.class, id);
        return result;
    }

}
