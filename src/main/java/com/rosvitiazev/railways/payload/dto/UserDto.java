package com.rosvitiazev.railways.payload.dto;

import com.rosvitiazev.railways.entity.Passenger;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Float balance;
    private String role;

    private Set<Passenger> passengers = new HashSet<>();
}
