package com.rosvitiazev.railways.services.interfaces;

import com.rosvitiazev.railways.domain.entities.Train;

import java.util.List;

public interface TrainService {
    public Train addTrain(int id, String number, int count_of_seats );
    public List<Train> getAllTrains();
    public void sendMessage(int id) ;
}
