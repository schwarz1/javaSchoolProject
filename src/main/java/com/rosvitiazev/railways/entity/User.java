package com.rosvitiazev.railways.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "users")
public class User {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "id")
    private Long id;
    @jakarta.persistence.Column(name = "login")
    private String login;
    @jakarta.persistence.Column(name = "password")
    private String password;
    @jakarta.persistence.Column(name = "email")
    private String email;
    @jakarta.persistence.Column(name = "balance")
    private Float balance;
    @jakarta.persistence.Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Passenger> passengers = new HashSet<>();
}
