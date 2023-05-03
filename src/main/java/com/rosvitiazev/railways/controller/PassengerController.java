package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.entity.User;
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


    @GetMapping("/create/{ticketId}")
    public String createPassengerForm(@PathVariable("ticketId") Long ticketId,Model model) {
        Passenger passenger = new Passenger();
        passenger.setTicket(ticketService.getTicketById(ticketId));
        model.addAttribute("ticket", ticketService.getTicketById(ticketId));
        model.addAttribute("passenger", passenger);
        return "passenger/create-passenger";
    }
    @PostMapping("/create-passenger")
    public String createPassenger(@ModelAttribute("passenger") Passenger passenger) {
        Ticket ticketPas = ticketService.getTicketById(passenger.getTicket().getId());
        if(!ticketService.existsByPassengerPassportAndTrainId(
                passenger.getPassportNumber(),
                ticketPas.getTrain().getId())) {
            User user = userService.getAauthenticationUser();
            if (userService.payTicket(ticketPas, user.getId())) {
                passenger.setTicket(ticketPas);
                passenger.setUser(user);
                passengerService.createPassenger(passenger);
                ticketService.sellTicket(ticketPas, passenger);
                return "redirect:/schedules";
            }
            return "passenger/bad-buy";
        }
        return "passenger/bad-buy";
    }

}