package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.repository.TrainRepository;
import com.rosvitiazev.railways.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;

    @Override
    public void create(Train train) {
        trainRepository.save(train);
    }

    @Override
    public void update(Train train) {

        trainRepository.save(train);
    }

    @Override
    public Train getTrainId(Long id) {
        return trainRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Train", "id", id)));
    }

    @Override
    public List<Train> getTrainsAll() {
        Sort sort = Sort.by("number").ascending();
        Pageable pageable = PageRequest.of(0, 1000, sort);
        Page<Train> trainRepositoryAll = trainRepository.findAll(pageable);
        return trainRepositoryAll.getContent();
    }

    @Override
    public void delete(Long id) {
        trainRepository.delete(trainRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Train", "id", id))));
    }

    @Override
    public List<Train> getTrainsWithoutSchedule(List<Train> allTrains, List<Schedule> schedules) {
        if (existList(schedules)) {
            return allTrains;
        } else {
            List<Long> scheduledTrainIds = schedules.stream()
                    .map(schedule -> schedule.getTrain().getId())
                    .toList();
            return allTrains.stream()
                    .filter(train -> !scheduledTrainIds.contains(train.getId()))
                    .toList();
        }
    }

    private boolean existList(List<Schedule> schedules) {
        return Objects.isNull(schedules);
    }
}