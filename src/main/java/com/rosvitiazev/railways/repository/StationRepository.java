package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByNameIgnoreCase(String name);
}