package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.service.StationService;
import com.rosvitiazev.railways.service.impl.StationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class StationController {
    private final StationService stationService;

    @GetMapping("/stations")
    public String getAllStations(Model model) {
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "station/all-stations";
    }

    @GetMapping("/create/station")
    public String createStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "station/create-station";
    }

    @PostMapping("/create-station")
    public String createStation(Station station) {
        stationService.createStation(station);
        return "redirect:/admin/stations";
    }

    @GetMapping("/update/{id}")
    public String updateStationForm(@PathVariable("id") Long id, Model model) {
        Station station = stationService.getStationById(id);
        model.addAttribute("station", station);
        return "station/update-station";
    }

    @PostMapping("/{id}")
    public String updateStation(Station station) {
        stationService.updateStation(station);
        return "redirect:/admin/stations";
    }

    @GetMapping("stations-delete/{id}")
    public String deleteStation(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            stationService.deleteStation(id);
            return "redirect:/admin/stations";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "You can't delete a station while it has a timetable");
            return "redirect:/admin/stations";
        }
    }

}