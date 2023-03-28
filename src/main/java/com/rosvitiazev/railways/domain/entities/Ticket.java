package com.rosvitiazev.railways.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "ticket")

public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotNull
    @Column(name = "departure_date")
    private LocalDate departure_date;

    @Column(name = "cost")
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private int train_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "station_id_departure")
    private int station_id_departure;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "station_id_destination")
    private int station_id_destination;

    @OneToMany
    @JoinColumn(name = "user_ticket")
    private String user_ticket;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "train_id")
    private Train train;

    public Ticket(Passenger passenger, String number, LocalDate departure_date) {
    }

    public Ticket(Passenger passenger, String number, Instant departureDate) {
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getStation_id_departure() {
        return station_id_departure;
    }

    public void setStation_id_departure(int station_id_departure) {
        this.station_id_departure = station_id_departure;
    }

    public int getStation_id_destination() {
        return station_id_destination;
    }

    public void setStation_id_destination(int station_id_destination) {
        this.station_id_destination = station_id_destination;
    }

    public String getUser_ticket() {
        return user_ticket;
    }

    public void setUser_ticket(String user_ticket) {
        this.user_ticket = user_ticket;
    }

    public Ticket(int id,
                  LocalDate departure_date,
                  BigDecimal cost,
                  int train_id,
                  Passenger passenger,
                  int station_id_departure,
                  int station_id_destination,
                  String user_ticket) {
        this.id = id;
        this.departure_date = departure_date;
        this.cost = cost;
        this.train_id = train_id;
        this.passenger = passenger;
        this.station_id_departure = station_id_departure;
        this.station_id_destination = station_id_destination;
        this.user_ticket = user_ticket;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", departure_date=" + departure_date +
                ", cost=" + cost +
                ", train_id=" + train_id +
                ", passenger=" + passenger +
                ", station_id_departure=" + station_id_departure +
                ", station_id_destination=" + station_id_destination +
                ", user_ticket='" + user_ticket + '\'' +
                '}';
    }
}
