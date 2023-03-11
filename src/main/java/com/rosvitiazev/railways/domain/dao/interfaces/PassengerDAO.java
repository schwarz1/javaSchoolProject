package com.rosvitiazev.railways.domain.dao.interfaces;

import com.rosvitiazev.railways.domain.entities.Passenger;

import java.util.List;

public interface PassengerDAO {

    public Passenger save(Passenger entity);

    public List<Passenger> findAll();

    public Passenger delete(Passenger entity);

    public Passenger update(Passenger entity);

    public Passenger findById(int id);

}
