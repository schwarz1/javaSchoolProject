package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Station;
import com.rosvitiazev.railways.service.impl.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;

    @PostMapping
    public String createStation(@RequestBody Station station) {
        stationService.createStation(station);
        return "redirect:/stations";
    }

    @GetMapping("/{id}")
    public String getStationById(@PathVariable("id") Long id, Model model) {
        Station station = stationService.getStationById(id);
        model.addAttribute("station", station);
        return "station-details";
    }

    @GetMapping
    public String getAllStations(Model model) {
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "all-stations";
    }

    @PutMapping("/{id}")
    public String updateStation(@PathVariable("id") Long id, @RequestBody Station station) {
        station.setId(id);
        stationService.updateStation(station);
        return "redirect:/stations";
    }

    @DeleteMapping("/{id}")
    public String deleteStation(@PathVariable("id") Long id) {
        stationService.deleteStation(id);
        return "redirect:/stations";
    }
}

