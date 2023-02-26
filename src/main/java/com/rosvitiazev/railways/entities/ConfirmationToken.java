package com.rosvitiazev.railways.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private LocalDate created_at;

    @Column(name = "expires_at")
    private LocalDate expires_at;

    @Column(name = "confirmed_at")
    private LocalDate confirmed_at;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    public ConfirmationToken() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(LocalDate expires_at) {
        this.expires_at = expires_at;
    }

    public LocalDate getConfirmed_at() {
        return confirmed_at;
    }

    public void setConfirmed_at(LocalDate confirmed_at) {
        this.confirmed_at = confirmed_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConfirmationToken(int id, String token, LocalDate created_at, LocalDate expires_at, LocalDate confirmed_at) {
        this.id = id;
        this.token = token;
        this.created_at = created_at;
        this.expires_at = expires_at;
        this.confirmed_at = confirmed_at;
    }

    @Override
    public String toString() {
        return "ConfirmationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", created_at=" + created_at +
                ", expires_at=" + expires_at +
                ", confirmed_at=" + confirmed_at +
                '}';
    }
}
