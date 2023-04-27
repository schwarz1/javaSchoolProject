package com.rosvitiazev.railways.repository;

import com.rosvitiazev.railways.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}