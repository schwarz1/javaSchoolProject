package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.service.impl.StationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StationController {
    private final StationServiceImpl stationService;

    @GetMapping("/stations")
    public String getAllStations(Model model) {
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "station/all-stations";
    }

    @GetMapping("/create")
    public String createStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "station/create-station";
    }

    @PostMapping("/create-station")
    public String createStation(Station station) {
        stationService.createStation(station);
        return "redirect:/stations";
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
        return "redirect:/stations";
    }

    @GetMapping("stations-delete/{id}")
    public String deleteStation(@PathVariable("id") Long id) {
        stationService.deleteStation(id);
        return "redirect:/stations";
    }
}



