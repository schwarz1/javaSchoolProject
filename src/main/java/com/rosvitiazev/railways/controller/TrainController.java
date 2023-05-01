package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;



    @GetMapping("/trains-list")
    public String getAllTrains(Model model) {
        model.addAttribute("trains", trainService.getTrainsAll());
        return "train/trains";
    }

    @GetMapping("/trains-list/{id}")
    public String getTrainById(@PathVariable Long id, Model model) {
        model.addAttribute("train", trainService.getTrainId(id));
        return "train/train";
    }

    @GetMapping("/trains-list/new")
    public String showCreateForm(Model model) {
        model.addAttribute("train", new Train());
        return "train/create-train";
    }

    @PostMapping("/trains-list")
    public String createTrain(@ModelAttribute Train train) {
        trainService.create(train);
        return "redirect:trains-list";
    }

    @GetMapping("/train-update/{id}")
    public String trainUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("train", trainService.getTrainId(id));
        return "train/update-train";
    }

    @PostMapping("/update-train")
    public String updateTrain( @ModelAttribute Train train) {
        trainService.update( train);
        return "redirect:trains-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteTrain(@PathVariable Long id) {
        trainService.delete(id);
        return "redirect:/trains-list";
    }
}