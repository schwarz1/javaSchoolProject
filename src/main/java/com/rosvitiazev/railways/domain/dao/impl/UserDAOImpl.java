package com.rosvitiazev.railways.domain.dao.impl;


import com.rosvitiazev.railways.domain.dao.interfaces.UserDAO;
import com.rosvitiazev.railways.domain.entities.User;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User save(User entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save user: " + ex);
        }
        return entity;
    }

    @Override
    public List<User> findAll() {
        List<User> result = null;
        TypedQuery<User> query = manager.createNamedQuery("User.findAll", User.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public User delete(User entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove user: " + ex);
        }
        return entity;
    }

    @Override
    public User update(User entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update user: " + ex);
        }
        return entity;
    }

    @Override
    public User findById(int id) {
        User result = manager.find(User.class, id);
        return result;
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = manager.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        User result = query.getSingleResult();
        return result;
    }
}
