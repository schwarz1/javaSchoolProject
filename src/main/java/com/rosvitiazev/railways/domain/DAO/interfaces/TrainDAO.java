package com.rosvitiazev.railways.domain.DAO.interfaces;

import com.rosvitiazev.railways.domain.entities.Train;

import java.util.List;

public interface TrainDAO {

    public Train findByNumber(String number);

    public Train save(Train entity);

    public List<Train> findAll();

    public Train delete(Train entity);

    public Train update(Train entity);

    public Train findById(int id);


}
