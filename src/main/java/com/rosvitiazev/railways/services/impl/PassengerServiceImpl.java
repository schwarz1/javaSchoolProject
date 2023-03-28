package com.rosvitiazev.railways.services.impl;

import com.rosvitiazev.railways.domain.dao.interfaces.PassengerDAO;
import com.rosvitiazev.railways.domain.dao.interfaces.TicketDAO;
import com.rosvitiazev.railways.domain.dao.interfaces.TrainDAO;
import com.rosvitiazev.railways.domain.dao.interfaces.UserDAO;
import com.rosvitiazev.railways.domain.entities.Passenger;
import com.rosvitiazev.railways.domain.entities.Train;
import com.rosvitiazev.railways.exception.CustomSQLException;
import com.rosvitiazev.railways.services.interfaces.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private TrainDAO trainDAO;

    private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);


    @Override
    public Passenger getPassengerById(int id) {
        Passenger result = passengerDAO.findById(id);
        return result;
    }

    public List<Passenger> getAllPassengers(String number, LocalDate departure_date) throws CustomSQLException {

        return ticketDAO.getAllPassengersByTrain(number, departure_date);
    }

    public List<Train> getAllTrains() throws CustomSQLException {

        List<Train> list = trainDAO.findAll();
        return list;
    }

    @Override
    public void createPassenger(int id, String firstName, String lastName, String email, String passportNumber) {

    }
}
