package com.rosvitiazev.railways.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train")

public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 0, max = 255)
    @Column(name = "number")
    private String number;

    @Column(name = "departure_time")
    private LocalDate departure_time;

    @Column(name = "count_of_seats")
    private int count_of_seats;

    @NotNull
    @Column(name = "cancelled")
    private boolean cancelled;

    @ManyToOne
    @JoinColumn(name = "route")
    private int route;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> ticketList = new ArrayList<>();

    public Train() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalDate departure_time) {
        this.departure_time = departure_time;
    }

    public int getCount_of_seats() {
        return count_of_seats;
    }

    public void setCount_of_seats(int count_of_seats) {
        this.count_of_seats = count_of_seats;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    public Train(int id, String number, LocalDate departure_time, int count_of_seats, boolean cancelled, int route, List<Ticket> ticketList) {
        this.id = id;
        this.number = number;
        this.departure_time = departure_time;
        this.count_of_seats = count_of_seats;
        this.cancelled = cancelled;
        this.route = route;
        this.ticketList = ticketList;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", departure_time=" + departure_time +
                ", count_of_seats=" + count_of_seats +
                ", cancelled=" + cancelled +
                ", route=" + route +
                '}';
    }
}
