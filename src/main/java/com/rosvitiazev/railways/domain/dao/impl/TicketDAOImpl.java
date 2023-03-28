package com.rosvitiazev.railways.domain.dao.impl;


import com.rosvitiazev.railways.domain.dao.interfaces.TicketDAO;
import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.domain.entities.Ticket;
import com.rosvitiazev.railways.exception.CustomSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository("trainDAO")
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Ticket save(Ticket entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't save ticket: " + ex);
        }
        return entity;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> result = null;
        TypedQuery<Ticket> query = manager.createNamedQuery("Ticket.findAll", Ticket.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public Ticket delete(Ticket entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't remove ticket: " + ex);
        }
        return entity;
    }

    @Override
    public Ticket update(Ticket entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can't update ticket: " + ex);
        }
        return entity;
    }

    @Override
    public Ticket findById(int id) {
        Ticket result = manager.find(Ticket.class, id);
        return result;
    }

    @Override
    public int countTicketOnTrain(String trainNumber, LocalDate departure_date) {
        String query = "Select count(t) from Ticket t where t.number = :trainNumber and t.departure_date = :departure_date";
        Query q = manager.createQuery(query);

        q.setParameter("trainNumber", trainNumber);
        q.setParameter("departure_date", departure_date);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<Passenger> getAllPassengersByTrain(String trainNumber, LocalDate departure_date) {

        List<Passenger> passengers = new ArrayList<>();

        TypedQuery<Ticket> query = manager.createNamedQuery("Ticket.findTicketsByTrainNumber", Ticket.class);
        query.setParameter("trainNumber", trainNumber);
        query.setParameter("departure_date", departure_date);
        List<Ticket> tickets = query.getResultList();

        for (Ticket ticket : tickets) {
            passengers.add(ticket.getPassenger());
        }
        return passengers;
    }

    @Override
    public List<Ticket> getTicketByPassengerID(int passenger_id) {

        String query = "from Ticket where passenger.id = :passenger_id";
        Query q = manager.createQuery(query);
        q.setParameter("passenger_id", passenger_id);
        return q.getResultList();
    }

    @Override
    public int countTicketsOnTrain(String valueOf, LocalDate departure_dateFormat) {
        return 0;
    }


}
