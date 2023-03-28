package com.rosvitiazev.railways.controllers;

import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.services.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PassengerController {

    @Autowired
    private static PassengerService passengerService;


    @PostMapping(path = "/passenger")
    public @ResponseBody void createPassenger (
            @RequestParam int id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String email,
            @RequestParam String passport_number ) {

        passengerService.createPassenger(id, first_name, last_name, email, passport_number);
    }

    @GetMapping(path="/passenger")
    @ResponseBody
    public Passenger getPassengerById(@RequestParam int id){
        return passengerService.getPassengerById(id);
    }
}
