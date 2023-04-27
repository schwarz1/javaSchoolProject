package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

