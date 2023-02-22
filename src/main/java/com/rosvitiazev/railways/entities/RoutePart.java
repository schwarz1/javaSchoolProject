package com.rosvitiazev.railways.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "route_part")
public class RoutePart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
