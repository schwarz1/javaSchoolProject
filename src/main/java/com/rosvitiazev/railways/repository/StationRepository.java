package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StationRepository extends JpaRepository<Station,Long> {
    Station findByName(String name);
}