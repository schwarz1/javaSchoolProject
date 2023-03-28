package com.rosvitiazev.railways.services.impl;

import com.rosvitiazev.railways.domain.dao.interfaces.*;
import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.domain.entities.Ticket;
import com.rosvitiazev.railways.domain.entities.Train;
import com.rosvitiazev.railways.domain.entities.User;
import com.rosvitiazev.railways.exception.CustomSQLException;
import com.rosvitiazev.railways.services.interfaces.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private final MessageSource messageSource;

    private DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public BookServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public boolean checkTrainExistByTrainNumber(String number) {
        logger.debug("checkTrainExistByTrainNumber method was called");
        Train result = trainDAO.findByNumber(number);
        if (result!=null) return true;
        return false;
    }

    public Ticket saveTicket(String number,
                             int station_id_departure,
                             int station_id_destination,
                             LocalDate departure_date,
                             Passenger passenger) throws CustomSQLException {
        logger.debug("saveTicket method was called");
        Ticket ticket = new Ticket(passenger, number, departure_date);
        ticketDAO.save(ticket);
        return ticket;
    }

    public boolean checkVacantPlaces(String number, LocalDate departure_dateFormat) {
        logger.debug("checkVacantPlaces method was called");
        int tickets = ticketDAO.countTicketsOnTrain(String.valueOf(number), LocalDate.from(departure_dateFormat));
        int ticketsAvailable = trainDAO.findByNumber(String.valueOf(number)).getCount_of_seats();
        return (ticketsAvailable <= tickets);
    }

    public boolean isPassengerAlreadyRegistered(String number, LocalDate departure_dateFormat, Passenger passenger) {
        logger.debug("isPassengerAlreadyRegistered method was called");
        List<Passenger> passengerList = ticketDAO.getAllPassengersByTrain(String.valueOf(number), LocalDate.from(departure_dateFormat));
        return passengerList.contains(passenger);
    }

    public Passenger createPassenger(String first_name,
                                     String last_name,
                                     String birth_date,
                                     String passport_number) throws CustomSQLException {
        logger.debug("createPassenger method was called");
        Passenger passenger = new Passenger();
        passenger.setFirst_name(first_name);
        passenger.setLast_name(last_name);
        passenger.setBirth_date(LocalDate.parse(birth_date));
        passenger.setPassport_number(passport_number);

        User user = userDAO.findById(passenger.getId());

        passenger.setUser(user);

        Passenger result = passengerDAO.save(passenger);
        return result;
    }


    public String payTicketProcess(String first_name,
                                   String last_name,
                                   String birth_date,
                                   String number,
                                   int station_id_departure,
                                   int station_id_destination,
                                   String departure_date,
                                   String departure_time,
                                   String passport_number,
                                   HttpServletRequest request){
        logger.debug("payTicketProcess method was called");
        LocalDate birth_dateFormat = LocalDate.parse(birth_date, ddMMyyyyformatter);
        Instant BirthDateFormatInstant =  birth_dateFormat.atStartOfDay(ZoneId.of("UTC")).toInstant();
        LocalDate departure_dateFormat = LocalDate.parse(departure_date, ddMMyyyyformatter);
        Instant depdateFormatInstant =  departure_dateFormat.atStartOfDay(ZoneId.of("UTC")).toInstant();

        boolean checkTickets = true;
        boolean checkPassengers = true;
        boolean checkTime = true;
        boolean success = false;

        if (checkVacantPlaces(String.valueOf(Integer.parseInt(number)),departure_dateFormat)) {
            //if train has no available seats
            checkTickets = false;
        }
        if (isPassengerAlreadyRegistered(String.valueOf(Integer.parseInt(number)),
                departure_dateFormat,
                new Passenger(first_name, last_name, birth_dateFormat))) {
            checkPassengers = false;
        }

        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(10);
        String departure_date_time = departure_date + " " + departure_time;
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime departure_timeFormat = LocalDateTime.parse(departure_time, formatterDateTime);
        if ( currentTime.isAfter(departure_timeFormat)) {
            checkTime = false;
        }

        if (checkTickets && checkPassengers && checkTime) {
            String username = request.getUserPrincipal().getName();
            Passenger passenger = createPassenger(first_name,
                    last_name,
                    String.valueOf(Instant.from(birth_dateFormat)),
                    passport_number);
            Ticket result = saveTicket(number,
                    station_id_departure,
                    station_id_destination,
                    LocalDate.from(departure_timeFormat),
                    passenger);
            success = true;
        }

        logger.debug("payTicketProcess method was called result " + success);

        String msg = messageSource.getMessage("unexpected_error", null, LocaleContextHolder.getLocale());
        String classIdent;
        if (success) {
            classIdent = "alert-success";
            msg = messageSource.getMessage("success_processpay", null, LocaleContextHolder.getLocale());
        } else {
            classIdent = "alert-danger";
            if (!checkTickets)
                msg = messageSource.getMessage("no_seats", null, LocaleContextHolder.getLocale());

            if (!checkPassengers)
                msg = messageSource.getMessage("passengerAlreadyExists", null, LocaleContextHolder.getLocale());

            if (!checkTime)
                msg = messageSource.getMessage("less10min", null, LocaleContextHolder.getLocale());

        }

        return msg;
    }
}

