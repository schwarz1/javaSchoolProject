package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl {
    public final TrainRepository trainRepository;

    public void create(Train train){
        trainRepository.save(train);
    }
    public void update(Long id, Train train){
        Train newTrain = trainRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Train", "id",id )));
        trainRepository.save(train);
    }
    public Train getTrainId(Long id){
        return trainRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Train", "id",id )));
    }
    public List<Train> getTrainsAll(){
        Sort sort = Sort.by("number").ascending();
        Pageable pageable = PageRequest.of(0, 1000, sort);
        Page<Train> trainRepositoryAll =trainRepository.findAll(pageable);
        return trainRepositoryAll.getContent();
    }

    public void delete(Long id){
        trainRepository.delete(trainRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Train", "id",id ))));
    }
}