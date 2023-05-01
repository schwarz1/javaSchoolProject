package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.repository.TicketRepository;
import com.rosvitiazev.railways.service.PassengerService;
import com.rosvitiazev.railways.service.TicketService;
import com.rosvitiazev.railways.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;
    private final TicketService ticketService;
    private final UserService userService;
    private final TicketRepository ticketRepository;

    @GetMapping("/{id}")
    public String getPassengerById(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id);
        model.addAttribute("passenger", passenger);
        return "passenger/passenger-details";
    }

    @GetMapping("/all")
    public String getAllPassengers(@RequestParam Long trainId, Model model) {
        List<Passenger> passengers = passengerService.getAllPassenger(trainId);
        model.addAttribute("passengers", passengers);
        return "passenger/all-passengers";
    }

    @GetMapping("/create/{ticketId}")
    public String createPassengerForm(@PathVariable("ticketId") Long ticketId,Model model) {
        Passenger passenger = new Passenger();
        passenger.setTicket(ticketService.getTicketById(ticketId));
        model.addAttribute("ticket", ticketService.getTicketById(ticketId));
        model.addAttribute("passenger", passenger);
        return "passenger/create-passenger";
    }
    //TODO добавить реализацию покупки после добавления пользователя
    @PostMapping("/create-passenger")
    public String createPassenger(@ModelAttribute("passenger") Passenger passenger) {
        Ticket ticketPas = ticketService.getTicketById(passenger.getTicket().getId());
        if(!ticketService.existsByPassengerPassportAndTrainId(
                passenger.getPassportNumber(),
                ticketPas.getTrain().getId())) {
            passenger.setTicket(ticketPas);
            passengerService.createPassenger(passenger);
            ticketService.sellTicket(ticketPas, passenger);
            /*
            if (userService.payTicket(ticketPas.getPrice(), passenger.getUser().getId())) {
                passenger.setTicket(ticketPas);
            }*/
            return "redirect:/schedules";
        }
        return "passenger/bad-buy";
    }

    @GetMapping("/{id}/update-passenger")
    public String updatePassengerForm(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id);
        model.addAttribute("passenger", passenger);
        return "passenger/update-passenger";
    }

    @PostMapping("/update-passenger")
    public String updatePassenger(@ModelAttribute("passenger") Passenger passenger) {
        passengerService.updatePassenger(passenger);
        return "redirect:/passengers/all?trainId=" + passenger.getTicket().getTrain().getId();
    }

    @GetMapping("/{id}/delete-passenger")
    public String deletePassenger(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        passengerService.deletePassenger(id);
        return "redirect:/passengers/all?trainId=" + passenger.getTicket().getTrain().getId();
    }

}

