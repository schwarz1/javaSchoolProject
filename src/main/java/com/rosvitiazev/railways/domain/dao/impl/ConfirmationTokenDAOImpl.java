package com.rosvitiazev.railways.domain.dao.impl;

import com.rosvitiazev.railways.domain.dao.interfaces.ConfirmationTokenDAO;
import com.rosvitiazev.railways.domain.entities.ConfirmationToken;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("confirmationTokenDAO")
public class ConfirmationTokenDAOImpl implements ConfirmationTokenDAO {

    @PersistenceContext
    private EntityManager manager;

    public ConfirmationTokenDAOImpl() {
    }

    @Override
    public ConfirmationToken save(ConfirmationToken entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save confirmationToken: " + ex);
        }
        return entity;
    }

    @Override
    public List<ConfirmationToken> findAll() {
        TypedQuery<ConfirmationToken> query = manager.createNamedQuery("ConfirmationToken.GetAllTokenList", ConfirmationToken.class);
        List<ConfirmationToken> result = query.getResultList();
        return result;
    }

    @Override
    public ConfirmationToken delete(ConfirmationToken entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove confirmationToken: " + ex);
        }
        return entity;
    }

    @Override
    public ConfirmationToken update(ConfirmationToken entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update confirmationToken: " + ex);
        }
        return entity;
    }

    @Override
    public ConfirmationToken findById(int id) {
        ConfirmationToken result = manager.find(ConfirmationToken.class, id);
        return result;
    }

    @Override
    public ConfirmationToken getUser(int user_id) {
        ConfirmationToken result;
        try {
            TypedQuery<ConfirmationToken> query = manager.createNamedQuery("ConfirmationToken.GetUserById", ConfirmationToken.class);
            query.setParameter("user_id", user_id);
            result = query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new CustomSQLException(ex.getMessage());
        }
        return result;
    }

}
