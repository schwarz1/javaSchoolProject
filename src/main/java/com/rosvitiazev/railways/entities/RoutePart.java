package com.rosvitiazev.railways.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route_part")

public class RoutePart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "time_passing")
    private LocalDate time_passing;

    @ManyToOne
    @JoinColumn(name = "station_id_from")
    private int station_id_from;

    @ManyToOne
    @JoinColumn(name ="station_id_to")
    private int station_id_to;

    @OneToMany(mappedBy = "routePart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Route> routeList = new ArrayList<>();

    public RoutePart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getTime_passing() {
        return time_passing;
    }

    public void setTime_passing(LocalDate time_passing) {
        this.time_passing = time_passing;
    }

    public int getStation_id_from() {
        return station_id_from;
    }

    public void setStation_id_from(int station_id_from) {
        this.station_id_from = station_id_from;
    }

    public int getStation_id_to() {
        return station_id_to;
    }

    public void setStation_id_to(int station_id_to) {
        this.station_id_to = station_id_to;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public RoutePart(int id, double price, LocalDate time_passing, int station_id_from, int station_id_to) {
        this.id = id;
        this.price = price;
        this.time_passing = time_passing;
        this.station_id_from = station_id_from;
        this.station_id_to = station_id_to;
    }

    @Override
    public String toString() {
        return "RoutePart{" +
                "id=" + id +
                ", price=" + price +
                ", time_passing=" + time_passing +
                ", station_id_from=" + station_id_from +
                ", station_id_to=" + station_id_to +
                '}';
    }
}

