package com.rosvitiazev.railways.entities;

import javax.persistence.*;

@Entity
@Table(name = "id")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
