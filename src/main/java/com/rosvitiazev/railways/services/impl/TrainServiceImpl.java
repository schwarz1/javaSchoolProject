package com.rosvitiazev.railways.services.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rosvitiazev.railways.domain.dao.interfaces.TrainDAO;
import com.rosvitiazev.railways.domain.entities.Train;
import com.rosvitiazev.railways.services.interfaces.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TrainServiceImpl implements TrainService {
    private final TrainDAO trainDAO;
    private final static String QUEUE_NAME = "hello";
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public TrainServiceImpl(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Override
    public Train addTrain(int id, String number, int count_of_seats) {
        logger.debug("addTrain method was called");
        List<Train> schedules = new ArrayList<>();
        schedules.add(new Train());
        Train train = new Train(id,number,count_of_seats);
        return trainDAO.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        logger.debug("getAllTrains method was called");
        List<Train> list = trainDAO.findAll();
        return list;
    }

    @Override
    public void sendMessage(int id) {

        logger.debug("sendMessage method was called");
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            logger.info(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (TimeoutException e){
            System.out.println(e);
        }


    }
}
