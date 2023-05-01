package com.rosvitiazev.railways.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    @Column(name="free_seats")
    private Boolean freeSeats;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

  /*  @ManyToOne
    @JoinColumn(name = "schedules_id")
    private Schedule schedule;*/
      /*  @ManyToOne
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;

    @ManyToOne
    @JoinColumn(name = "finish_station_id")
    private Station arrivalStation;
*/
}

