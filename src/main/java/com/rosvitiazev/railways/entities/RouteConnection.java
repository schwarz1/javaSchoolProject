package com.rosvitiazev.railways.entities;

import javax.persistence.*;

@Entity
@Table(name = "route)connection")
public class RouteConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
