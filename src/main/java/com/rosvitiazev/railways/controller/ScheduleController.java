package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final StationService stationService;
    private final TrainService trainService;

    @GetMapping("/schedules")
    public String getAllSchedule(Model model, Principal principal) {
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        model.addAttribute("schedules", scheduleList);
        var user = userService.getUserByPrincipal(principal);
        if (Objects.isNull(user.getEmail())) {
            return "schedule/all-schedules";
        } else {
            model.addAttribute("user", userService.getAauthenticationUser());
            return "schedule/all-schedules";
        }
    }

    @GetMapping("/schedules/departureStation/arrivalStation")
    public String getAllScheduleByStationDepartureAndArrival(@RequestParam("departureStation") String departureStation,
                                                             @RequestParam(value = "arrivalStation", required = false) String arrivalStation,
                                                             Model model) {
        List<Schedule> scheduleList = scheduleService
                .getAllScheduleByStationDepartureStationAndArrival(departureStation, arrivalStation);
        model.addAttribute("user", userService.getAauthenticationUser());
        model.addAttribute("schedules", scheduleList);
        return "schedule/all-schedules";
    }

    @GetMapping("/schedules/date")
    public String getAllScheduleByDepartureTime(@RequestParam("departureTime") String departureTime,
                                                Model model) {
        LocalDate localDate = LocalDate.parse(departureTime);
        List<Schedule> scheduleList = scheduleService.getAllScheduleByDepartureTime(localDate);
        model.addAttribute("user", userService.getAauthenticationUser());
        model.addAttribute("schedules", scheduleList);
        return "schedule/all-schedules";
    }

    @GetMapping("admin/create-schedule")
    public String createScheduleForm(Model model) {
        List<Station> stations = stationService.getAllStations();
        List<Train> trains = trainService.getTrainsAll();
        model.addAttribute("allStations", stations);
        model.addAttribute("allTrains", trainService.getTrainsWithoutSchedule(
                trains, scheduleService.getAllScheduleNotSort()));
        model.addAttribute("schedule", new Schedule());
        return "schedule/create-schedule";
    }

    @PostMapping("admin/create-schedule")
    public String createSchedule(@ModelAttribute("schedule") Schedule schedule, RedirectAttributes redirectAttributes) {
        if (schedule.getDepartureTime() == null || schedule.getArrivalTime() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "check that the fields are filled in correctly");
            return "redirect:/admin/create-schedule";
        } else {
            scheduleService.createSchedule(schedule);
            return "redirect:/schedules";
        }
    }

    @GetMapping("admin/schedules-update/{id}")
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
        model.addAttribute("allTrains", trainService.getTrainsWithoutSchedule(
                trains, scheduleService.getAllSchedule()));
        return "schedule/update-schedule";
    }

    @PostMapping("admin/update-schedules")
    public String updateSchedule(@ModelAttribute("schedule") Schedule schedule,
                                 RedirectAttributes redirectAttributes) {
        if (schedule.getDepartureTime() == null || schedule.getArrivalTime() == null||schedule.getTrain()==null) {
            redirectAttributes.addAttribute("errorMessage", "check that the fields are filled in correctly");
            return "redirect:/admin/update-schedules";
        } else {
            scheduleService.updateSchedule(schedule);
            return "redirect:/schedules";
        }
    }

    @GetMapping("admin/schedules-delete/{id}")
    public String deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.delete(id);
        return "redirect:/schedules";
    }

}