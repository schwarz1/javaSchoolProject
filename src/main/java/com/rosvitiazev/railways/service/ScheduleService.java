package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedule();
    List<Schedule> getAllScheduleByStationDepartureStationAndArrival(String departureStation,String stationArrival);
    List<Schedule> getAllScheduleByStationDeparture(String departureStation);
    List<Schedule> getAllScheduleByDepartureTime(LocalDate departureTime);
    /* List<Schedule> getAllScheduleByDepartureTimeAndArrivalTime(LocalDate departureTime);
     */
}
