package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.service.TicketService;
import com.rosvitiazev.railways.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TrainService trainService;

    @GetMapping("/trains/{trainId}/free-seats")
    public String getFreeSeatsOnTheTrain(@PathVariable Long trainId, Model model) {
        List<Ticket> freeSeats = ticketService.getFreeSeatsOnTheTrain(trainId);
        model.addAttribute("freeSeats", freeSeats);
        return "ticket/train-free-seats";
    }

    @GetMapping("admin/ticket-create/{TrainId}")
    public String createTicketForm(@PathVariable("TrainId") Long trainId, Model model) {
        Train train = trainService.getTrainId(trainId);
        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        model.addAttribute("train", train);
        model.addAttribute("ticket", ticket);
        return "ticket/create-ticket";
    }

    @PostMapping("/create-ticket")
    public String createTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.createTicket(ticket);
        return "redirect:admin/trains-list";
    }

    @PostMapping("/update-ticket")
    public String updateTicket(Ticket ticket) {
        ticketService.updateTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}