package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/start")
public class StartController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedules")
    public String getAllScheduleStart(Model model) {
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        model.addAttribute("schedules", scheduleList);
        return "start/schedules-start";
    }
    @GetMapping("/schedules/departureStation/arrivalStation")
    public String getAllScheduleByStationDepartureAndArrival(@RequestParam("departureStation") String departureStation,
                                                             @RequestParam(value = "arrivalStation", required = false) String arrivalStation,
                                                             Model model) {
        List<Schedule> scheduleList = scheduleService
                .getAllScheduleByStationDepartureStationAndArrival(departureStation, arrivalStation);
        model.addAttribute("schedules", scheduleList);
        return "start/schedules-start";
    }

    @GetMapping("/schedules/date")
    public String getAllScheduleByDepartureTime(@RequestParam("departureTime") String departureTime,
                                                Model model) {
        LocalDate localDate = LocalDate.parse(departureTime);
        List<Schedule> scheduleList = scheduleService.getAllScheduleByDepartureTime(localDate);
        model.addAttribute("schedules", scheduleList);
        return "start/schedules-start";
    }
}

