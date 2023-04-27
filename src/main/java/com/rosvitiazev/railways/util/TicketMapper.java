package com.rosvitiazev.railways.util;

import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.payload.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketMapper {
    private final ModelMapper modelMapper;

    public Ticket mapToEntity(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, Ticket.class);
    }

    public TicketDto mapToDTO(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }
}
