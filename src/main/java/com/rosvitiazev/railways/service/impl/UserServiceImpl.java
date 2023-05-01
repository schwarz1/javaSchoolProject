package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.User;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.repository.PassengerRepository;
import com.rosvitiazev.railways.repository.UserRepository;
import com.rosvitiazev.railways.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;

    @Override
    public void createUser(User user) {

    }

    @Override
    public Boolean payTicket(Double price, Long userId) {
        User user = userRepository.findById(userId).orElseThrow((() ->
                new ResourceNotFoundException("User", "id", userId)));
        Double balance = user.getBalance();
        user.setBalance(user.getBalance() - price);
        userRepository.save(user);
        User payUser = userRepository.findById(userId).orElseThrow((() ->
                new ResourceNotFoundException("User", "id", userId)));
        return price == balance - payUser.getBalance();
    }
}
