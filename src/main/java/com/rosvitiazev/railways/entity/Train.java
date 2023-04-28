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
@Table(name="trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="number")
    private String number;



    @ManyToOne
    @JoinColumn(name = "route_sheet_id")
    private RouteSheet routeSheet;

    @OneToMany(mappedBy = "train")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "train")
    private Set<Ticket> tickets = new HashSet<>();
}