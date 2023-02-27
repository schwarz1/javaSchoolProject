package com.rosvitiazev.railways.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "passenger")

public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "first_name")
    private String first_name;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "last_name")
    private String last_name;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birth_date;

    @NotEmpty
    @Size(min = 0, max = 15)
    @Column(name = "mobile_phone")
    private String mobile_phone;

    @NotEmpty
    @Size(min = 0, max = 15)
    @Column(name = "passport_number")
    private String passport_number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> ticketList;

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(Set<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Passenger(String first_name,
                     String last_name,
                     LocalDate birth_date,
                     String mobile_phone,
                     String passport_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.mobile_phone = mobile_phone;
        this.passport_number = passport_number;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passenger_id=" + id +
                ", first_name='" + first_name +
                ", last_name='" + last_name +
                ", birth_date='" + birth_date +
                ", mobile_number='" + mobile_phone +
                ", passport_number='" + passport_number +
                '}';
    }
}
