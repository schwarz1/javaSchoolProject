package com.rosvitiazev.railways.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private int user_id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private int ticket_id;

    @Column(name = "payment_time")
    private LocalDate payment_time;

    public UserTicket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public LocalDate getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(LocalDate payment_time) {
        this.payment_time = payment_time;
    }

    public UserTicket(int id, int user_id, int ticket_id) {
        this.id = id;
        this.user_id = user_id;
        this.ticket_id = ticket_id;
    }

    @Override
    public String toString() {
        return "UserTicket{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", ticket_id=" + ticket_id +
                ", payment_time=" + payment_time +
                '}';
    }
}
