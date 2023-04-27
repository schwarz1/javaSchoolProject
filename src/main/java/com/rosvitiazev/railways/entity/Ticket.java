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
@Table(name="tikets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="price")
    private Double price;
    @Column(name="seat_number")
    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;

    @ManyToOne
    @JoinColumn(name = "finish_station_id")
    private Station arrivalStation;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;


    @OneToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @OneToMany(mappedBy = "ticket")
    private Set<Schedule> schedules= new HashSet<>();
}
