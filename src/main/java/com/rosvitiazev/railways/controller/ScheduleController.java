package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.service.ScheduleService;
import com.rosvitiazev.railways.service.StationService;
import com.rosvitiazev.railways.service.TicketService;
import com.rosvitiazev.railways.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final StationService stationService;
    private final TrainService trainService;
    private final TicketService ticketService;

    @GetMapping("/schedules")
    public String getAllSchedule(Model model) {
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        model.addAttribute("schedules", scheduleList);
        return "schedule/all-schedules";
    }

    @GetMapping("/schedules/departureStation/arrivalStation")
    public String getAllScheduleByStationDepartureAndArrival(@RequestParam("departureStation") String departureStation,
                                                             @RequestParam(value = "arrivalStation", required = false) String arrivalStation,
                                                             Model model) {

        List<Schedule> scheduleList = scheduleService
                .getAllScheduleByStationDepartureStationAndArrival(departureStation, arrivalStation);
        model.addAttribute("schedules", scheduleList);
        return "schedule/all-schedules";
    }

    @GetMapping("/schedules/date")
    public String getAllScheduleByDepartureTime(@RequestParam("departureTime") String departureTime,
                                                Model model) {
        LocalDate localDate = LocalDate.parse(departureTime);
        List<Schedule> scheduleList = scheduleService.getAllScheduleByDepartureTime(localDate);
        model.addAttribute("schedules", scheduleList);
        return "schedule/all-schedules";
    }

    @GetMapping("/create-schedule")
    public String createScheduleForm(Model model) {
        List<Station> stations = stationService.getAllStations();
        List<Train> trains = trainService.getTrainsAll();
        model.addAttribute("allStations", stations);
        model.addAttribute("allTrains", trains);
        model.addAttribute("schedule", new Schedule());
        return "schedule/create-schedule";
    }

    @PostMapping("/create-schedule")
    public String createSchedule(@ModelAttribute("schedule") Schedule schedule) {
        scheduleService.createSchedule(schedule);
        return "redirect:/schedules";
    }


    @GetMapping("/schedules-update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        List<Station> stations = stationService.getAllStations();
        List<Train> trains = trainService.getTrainsAll();
        model.addAttribute("previousDepartureStation", schedule.getDepartureStation().getId());
        model.addAttribute("previousArrivalStation", schedule.getArrivalStation().getId());
        model.addAttribute("previousTrain", schedule.getTrain().getId());
        model.addAttribute("previousDepartureTime", schedule.getDepartureTime());
        model.addAttribute("previousArrivalTime", schedule.getArrivalTime());
        model.addAttribute("allStations", stations);
        model.addAttribute("schedule", schedule);
        model.addAttribute("allTrains", trains);
        return "schedule/update-schedule";
    }


    @PostMapping("/update-schedules")
    public String updateSchedule(@ModelAttribute("schedule") Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/schedules-delete/{id}")
    public String deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.delete(id);
        return "redirect:/schedules";
    }
}
