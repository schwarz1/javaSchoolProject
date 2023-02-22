package com.rosvitiazev.railways.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "id")

public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "train_number")
    private String train_number;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "departure_date")
    private Date departure_date;


    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "station_id_departure")
    private int station_id_departure;

    @Column(name = "station_id_destination")
    private int station_id_destination;


    public int getTicket_id() {
        return id;
    }

    public void setTicket_id(int ticket_id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Ticket() {
    }

    public Ticket(int id, Passenger passenger, String train_number, Date departure_date) {
        this.id = id;
        this.passenger = passenger;
        this.train_number = train_number;
        this.departure_date = departure_date;
    }
}
