package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Passenger;
import com.rosvitiazev.railways.entity.Schedule;
import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.entity.Train;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.repository.PassengerRepository;
import com.rosvitiazev.railways.repository.TicketRepository;
import com.rosvitiazev.railways.repository.TrainRepository;
import com.rosvitiazev.railways.service.ScheduleService;
import com.rosvitiazev.railways.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;
    private final PassengerRepository passengerRepository;
    private final ScheduleService scheduleService;

    @Override
    public void createTicket(Ticket ticket) {
        Train train = trainRepository.findById(ticket.getTrain().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Train", "id",ticket.getTrain().getId()));
        if(!isExist(train)){
            for (int i = 1; i <=train.getSeatsNumber() ; i++) {
                Ticket saveTicket = new Ticket();
                saveTicket.setPrice(ticket.getPrice());
                saveTicket.setTrain(ticket.getTrain());
                saveTicket.setFreeSeats(true);
                saveTicket.setSeatNumber(i);
                ticketRepository.save(saveTicket);
            }
        }
    }
    private boolean isExist(Train train){
        List<Ticket> list = ticketRepository.findAllByTrainId(train.getId());
        return list.size()+1>train.getSeatsNumber();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "id",id ));
        ticketRepository.delete(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "id",id ));
    }

    @Override
    public List<Ticket> getFreeSeatsOnTheTrain(Long trainId) {
        Sort sort = Sort.by("seatNumber").ascending();
        Pageable pageable = PageRequest.of(0, 1000, sort);
        Page<Ticket> page= ticketRepository.findAllByTrainIdAndFreeSeats(trainId, true, pageable);
        return page.getContent();
    }

    @Override
    public void sellTicket(Ticket ticket, Passenger passenger) {
        Ticket ticketSell = ticketRepository.findById(ticket.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "id",ticket.getId() ));
        Passenger passengerSell = passengerRepository.findById(passenger.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Passenger", "id",passenger.getId() ));
        ticketSell.setFreeSeats(false);
        ticketSell.setPassenger(passengerSell);
        ticketRepository.save(ticketSell);

    }

    @Override
    public boolean existsByPassengerPassportAndTrainId(String passportNumber, Long trainId) {
        return ticketRepository.existsByPassengerPassportNumberAndTrainId(passportNumber,trainId);
    }

    @Override
    public boolean validTicket(Ticket ticket) {
        Schedule schedule = scheduleService.getScheduleByTrainId(ticket.getTrain().getId());
        LocalDateTime now = LocalDateTime.now();
        return schedule.getDepartureTime().isAfter(now.plus(Duration.ofMinutes(10)));
    }


}
