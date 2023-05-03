package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class TrainController {
    private final TrainService trainService;

    @GetMapping("/trains-list")
    public String getAllTrains(Model model) {
        model.addAttribute("trains", trainService.getTrainsAll());
        return "train/trains";
    }

    @GetMapping("/trains-list/new")
    public String showCreateForm(Model model) {
        model.addAttribute("train", new Train());
        return "train/create-train";
    }

    @PostMapping("/trains-list")
    public String createTrain(@ModelAttribute Train train) {
        trainService.create(train);
        return "redirect:/admin/trains-list";
    }

    @GetMapping("/train-update/{id}")
    public String trainUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("train", trainService.getTrainId(id));
        return "train/update-train";
    }

    @PostMapping("/update-train")
    public String updateTrain(@ModelAttribute Train train) {
        trainService.update(train);
        return "redirect:/admin/trains-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteTrain(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            trainService.delete(id);
            return "redirect:/admin/trains-list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "You can't delete a train while it has a timetable");
            return "redirect:/admin/trains-list";
        }
    }
}