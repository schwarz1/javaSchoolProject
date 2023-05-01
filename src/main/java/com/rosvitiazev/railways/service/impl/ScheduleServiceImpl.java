package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.repository.ScheduleRepository;
import com.rosvitiazev.railways.repository.StationRepository;
import com.rosvitiazev.railways.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final StationRepository stationRepository;

    @Override
    public List<Schedule> getAllSchedule() {
        if (isExistSchedule()) {
            return null;
        } else {
            Sort sort = Sort.by("departureTime").ascending();
            Pageable pageable = PageRequest.of(0, 1000, sort);
            Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);
            return actualSchedule(schedulePage.getContent());
        }
    }

    private boolean isExistSchedule() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        return scheduleList.size() == 0;
    }

    @Override
    public List<Schedule> getAllScheduleByStationDepartureStationAndArrival(String stationDeparture,
                                                                            String stationArrival
    ) {
        String s = "";
        if (s.equals(stationArrival)) {
            return getAllScheduleByStationDeparture(stationDeparture);
        } else {
            Sort sort = Sort.by("departureTime").ascending();
            Pageable pageable = PageRequest.of(0, 100, sort);
            Station deparStation = stationRepository.findByNameIgnoreCase(stationDeparture);
            Station arrivalStation = stationRepository.findByNameIgnoreCase(stationArrival);
            Page<Schedule> schedulePage = scheduleRepository.findByDepartureStationAndArrivalStation(
                    deparStation,
                    arrivalStation,
                    pageable);
            schedulePage.getContent();
            return actualSchedule(schedulePage.getContent());
        }
    }

    @Override
    public List<Schedule> getAllScheduleByStationDeparture(String departureStation) {
        Sort sort = Sort.by("departureTime").ascending();
        Pageable pageable = PageRequest.of(0, 100, sort);
        Station deparStation = stationRepository.findByNameIgnoreCase(departureStation);
        Page<Schedule> schedulePage = scheduleRepository.findAllByDepartureStation(deparStation, pageable);
        return actualSchedule(schedulePage.getContent());
    }

    @Override
    public List<Schedule> getAllScheduleByDepartureTime(LocalDate departureTime) {
        LocalTime startOfDay = LocalTime.MIN;
        LocalTime endOfDay = LocalTime.MAX;
        LocalDateTime start = LocalDateTime.of(departureTime, startOfDay);
        LocalDateTime end = LocalDateTime.of(departureTime, endOfDay);
        Sort sort = Sort.by("departureTime").ascending();
        Pageable pageable = PageRequest.of(0, 100, sort);
        Page<Schedule> schedulePage = scheduleRepository
                .findAllByDepartureTimeBetween(start, end, pageable);
        return actualSchedule(schedulePage.getContent());

    }

    @Override
    public void createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);

    }

    @Override
    public void updateSchedule(Schedule schedule) {

        scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.delete(scheduleRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Schedule", "id", id))));
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Schedule", "id", id)));
    }

    private List<Schedule> actualSchedule(List<Schedule> list) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(ss -> currentDateTime.isBefore(ss.getDepartureTime()))
                .toList();

    }
}
