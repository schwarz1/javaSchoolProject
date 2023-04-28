package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.service.impl.TrainServiceImpl;
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
    private final TrainServiceImpl trainService;

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
        return "redirect:/trains-list";
    }

    @GetMapping("/trains-list/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("train", trainService.getTrainId(id));
        return "train/edit-train";
    }

    @PostMapping("/trains-list/{id}")
    public String updateTrain(@PathVariable Long id, @ModelAttribute Train train) {
        trainService.update(id, train);
        return "redirect:/trains-list";
    }

    @GetMapping("/trains-list/{id}/delete")
    public String deleteTrain(@PathVariable Long id) {
        trainService.delete(id);
        return "redirect:/trains-list";
    }
}