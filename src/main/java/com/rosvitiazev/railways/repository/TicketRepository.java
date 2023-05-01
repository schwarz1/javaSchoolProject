package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByTrainId(Long id);

    Page<Ticket> findAllByTrainIdAndFreeSeats(Long trainId, boolean freeSeats, Pageable pageable);

    boolean existsByPassengerFirstNameAndPassengerLastNameAndTrainId(String firstName,
                                                                     String lastName,
                                                                     Long trainId);

    boolean existsByPassengerPassportNumberAndTrainId(String passportNumber,
                                                      Long trainId);

}