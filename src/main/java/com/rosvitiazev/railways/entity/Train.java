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
@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "seats_number")
    private Integer seatsNumber;
    @OneToOne(mappedBy = "train")
    private Schedule schedules;
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

}

