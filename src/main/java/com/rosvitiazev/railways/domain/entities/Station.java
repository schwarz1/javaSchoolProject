package com.rosvitiazev.railways.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station")
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Size(min = 3, max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "stop_duration")
    private LocalDate stop_duration;

    @Column(name = "closed")
    private boolean closed;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> station_id_departure = new ArrayList<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> station_id_destination = new ArrayList<>();

    public Station() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStop_duration() {
        return stop_duration;
    }

    public void setStop_duration(LocalDate stop_duration) {
        this.stop_duration = stop_duration;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public List<Ticket> getStation_id_departure() {
        return station_id_departure;
    }

    public void setStation_id_departure(List<Ticket> station_id_departure) {
        this.station_id_departure = station_id_departure;
    }

    public List<Ticket> getStation_id_destination() {
        return station_id_destination;
    }

    public void setStation_id_destination(List<Ticket> station_id_destination) {
        this.station_id_destination = station_id_destination;
    }

    public Station(int id,
                   String name,
                   LocalDate stop_duration,
                   boolean closed,
                   List<Ticket> station_id_departure,
                   List<Ticket> station_id_destination) {
        this.id = id;
        this.name = name;
        this.stop_duration = stop_duration;
        this.closed = closed;
        this.station_id_departure = station_id_departure;
        this.station_id_destination = station_id_destination;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stop_duration=" + stop_duration +
                ", closed=" + closed +
                ", station_id_departure=" + station_id_departure +
                ", station_id_destination=" + station_id_destination +
                '}';
    }
}
