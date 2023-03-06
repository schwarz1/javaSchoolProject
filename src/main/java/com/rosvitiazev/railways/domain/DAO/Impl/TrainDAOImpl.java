package com.rosvitiazev.railways.domain.DAO.Impl;

import com.rosvitiazev.railways.domain.DAO.interfaces.TrainDAO;
import com.rosvitiazev.railways.domain.entities.Train;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("trainDAO")
public class TrainDAOImpl implements TrainDAO {

    @PersistenceContext
    private EntityManager manager;

    public TrainDAOImpl() {
    }

    public Train findByNumber(String number) {

        TypedQuery<Train> query = manager.createNamedQuery("Train.findByNumber", Train.class);
        query.setParameter("trainNumber", number);
        Train result = query.getSingleResult();
        return result;
    }

    @Override
    @Transactional
    public Train save(Train entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save train: " + ex);
        }
        return entity;
    }

    public List<Train> findAll() {
        List<Train> result = null;
        TypedQuery<Train> query = manager.createNamedQuery("Train.findAll", Train.class);
        result = query.getResultList();
        return result;
    }

    public Train delete(Train entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove train: " + ex);
        }
        return entity;
    }

    public Train update(Train entity) {
        try {
            manager.merge(entity);
        } catch (PessimisticLockException ex) {
            throw new CustomSQLException("Can't update train: " + ex);
        }
        return entity;
    }

    public Train findById(int id) {
        Train result = manager.find(Train.class, id);
        return result;
    }

}
