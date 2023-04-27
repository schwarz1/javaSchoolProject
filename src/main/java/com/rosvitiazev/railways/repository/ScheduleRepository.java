package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Page<Schedule> findByDepartureStationAndStationArrival(Station departureStation,
                                                           Station arrivalStation,
                                                           Pageable pageable);
    Page<Schedule> findAllByDepartureStation(Station departureStation,  Pageable pageable);
    Page<Schedule> findAllByDepartureTimeBetween(LocalDateTime localDateTimeMin,
                                                 LocalDateTime localDateTimeMax,
                                                 Pageable pageable);
}

