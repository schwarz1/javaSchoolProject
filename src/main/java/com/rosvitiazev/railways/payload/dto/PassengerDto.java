package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.entity.User;
import lombok.Data;

@Data
public class PassengerDto {
    private Long id;

    private String lastName;
    private String firstName;
    private String passportNumber;
    private String mobilePhone;
    private User user;
    private Ticket ticket;
}
