package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByLoginOrEmail(String username, String email);

}

