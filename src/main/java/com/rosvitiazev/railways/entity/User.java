package com.rosvitiazev.railways.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login",unique = true)
    @NotBlank(message = "Login cannot be empty")
    private String login;
    @Column(name = "password")
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @Column(name = "email",unique = true)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not correct")
    private String email;
    @Column(name = "balance")
    @DecimalMin(value = "0.0", message = "Balance must be non-negative")
    private Double balance;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Passenger> passengers = new HashSet<>();
}
