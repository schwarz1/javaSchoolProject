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
@jakarta.persistence.Table(name = "stations")
public class Station {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "id")
    private Long id;
    @jakarta.persistence.Column(name = "name")
    private String name;
    @jakarta.persistence.ManyToMany(mappedBy = "stations")
    private Set<RouteSheet> routeSheets = new HashSet<>();

    @OneToMany(mappedBy = "departureStation")
    private Set<Schedule> schedules = new HashSet<>();
}
