package com.rosvitiazev.railways.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(name = "passenger_id")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Passenger passenger;


    public Passenger getPassenger() {
        return passenger;
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
