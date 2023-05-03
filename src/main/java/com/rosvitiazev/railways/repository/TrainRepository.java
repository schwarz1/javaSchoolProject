package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {

}