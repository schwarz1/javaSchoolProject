package com.rosvitiazev.railways.domain.DAO.Impl;

import com.rosvitiazev.railways.domain.DAO.interfaces.UserTicketDAO;
import com.rosvitiazev.railways.domain.entities.Ticket;
import com.rosvitiazev.railways.domain.entities.UserTicket;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("userTicketDAO")
public class UserTicketDAOImpl implements UserTicketDAO {

    @PersistenceContext
    private EntityManager manager;

    public UserTicketDAOImpl() {
    }

    @Transactional
    @Override
    public UserTicket save(UserTicket entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save userTicket: " + ex);
        }
        return entity;
    }

    @Override
    public List<UserTicket> findAll() {
        List<UserTicket> result = null;
        TypedQuery<UserTicket> query = manager.createNamedQuery("UserTicket.findAll", UserTicket.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public UserTicket delete(UserTicket entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove userTicket: " + ex);
        }
        return entity;
    }

    @Override
    public UserTicket update(UserTicket entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update userTicket: " + ex);
        }
        return entity;
    }

    @Override
    public UserTicket findById(int id) {
        UserTicket result = manager.find(UserTicket.class, id);
        return result;
    }

    @Override
    public List<Ticket> getTicketByUserId(int user_id) {

        String query = "from Ticket where user.user_id = :user_id";
        Query q = manager.createQuery(query);
        q.setParameter("user_id", user_id);
        return q.getResultList();
    }
}
