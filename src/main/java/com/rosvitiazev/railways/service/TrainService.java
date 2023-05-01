package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.entity.Train;

import java.util.List;

public interface TrainService {
    Train getTrainByNumber(String number);

    void create(Train train);

    void update(Train train);

    Train getTrainId(Long id);

    List<Train> getTrainsAll();

    void delete(Long id);

}
