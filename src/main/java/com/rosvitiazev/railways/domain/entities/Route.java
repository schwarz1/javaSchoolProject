package com.rosvitiazev.railways.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany
    @JoinColumn(name = "route_connection")
    private int route_connection;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "route_part_id")
    private RoutePart routePart;

    @ManyToMany(targetEntity = RoutePart.class, cascade = CascadeType.ALL)
    @JoinTable(name = "route_part",
            joinColumns = @JoinColumn(name = "route"), inverseJoinColumns = @JoinColumn(name = "route_part"))
    private Set<RoutePart> parts;


    public Route() {
    }

    public Route(int id, int route_connection) {
        this.id = id;
        this.route_connection = route_connection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoute_connection() {
        return route_connection;
    }

    public void setRoute_connection(int route_connection) {
        this.route_connection = route_connection;
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

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", route_connection=" + route_connection +
                '}';
    }
}
