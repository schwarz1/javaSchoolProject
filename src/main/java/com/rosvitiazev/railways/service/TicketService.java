package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.payload.dto.TicketDto;

import java.util.List;

public interface TicketService {
    void createTicket(TicketDto ticketDto);

    void updateTicket(TicketDto ticketDto, Long id);

    List<TicketDto> getAllFreeTicketByStationStartStationFinish(Long startStation,
                                                                Long finishStation,
                                                                String sortBy,
                                                                String sortDir);
    void deleteTicket(Long id);

}