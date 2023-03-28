package com.rosvitiazev.railways.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route implements Serializable {

    public static String login;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany
    @JoinColumn(name = "route_connection")
    private Set<Route> route_connection;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "route_part_id")
    private RoutePart routePart;

    @ManyToMany(targetEntity = RoutePart.class, cascade = CascadeType.ALL)
    @JoinTable(name = "route_part",
            joinColumns = @JoinColumn(name = "route"), inverseJoinColumns = @JoinColumn(name = "route_part"))
    private Set<RoutePart> parts;

    public Route() {
    }

    public Route(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Route> getRoute_connection() {
        return route_connection;
    }


    public RoutePart getRoutePart() {
        return routePart;
    }

    public void setRoutePart(RoutePart routePart) {
        this.routePart = routePart;
    }

    public Set<RoutePart> getParts() {
        return parts;
    }

    public void setParts(Set<RoutePart> parts) {
        this.parts = parts;
    }

    public Route(int id, RoutePart routePart) {
        this.id = id;
        this.routePart = routePart;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", route_connection=" + route_connection +
                '}';
    }
}
