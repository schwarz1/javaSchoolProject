package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByTrainId(Long trainId);

    Page<Schedule> findByDepartureStationAndArrivalStation(Station departureStation,
                                                           Station arrivalStation,
                                                           Pageable pageable);

    Page<Schedule> findAllByDepartureStation(Station departureStation, Pageable pageable);

    Page<Schedule> findAllByDepartureTimeBetween(LocalDateTime localDateTimeMin,
                                                 LocalDateTime localDateTimeMax,
                                                 Pageable pageable);


}

