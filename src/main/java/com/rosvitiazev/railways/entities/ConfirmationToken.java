package com.rosvitiazev.railways.entities;

import javax.persistence.*;

@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
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
