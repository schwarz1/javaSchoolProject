package com.rosvitiazev.railways.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @ManyToOne
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;
    @ManyToOne
    @JoinColumn(name = "station_arrival_id")
    private Station arrivalStation;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    private Train train;

}