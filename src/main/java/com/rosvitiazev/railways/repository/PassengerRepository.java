package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findAllByTicketTrainId(Long trainId);

}