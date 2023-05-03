package com.rosvitiazev.railways.service.impl;

import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.entity.User;
import com.rosvitiazev.railways.exception.ResourceNotFoundException;
import com.rosvitiazev.railways.exception.ResourceNotFoundExceptionByName;
import com.rosvitiazev.railways.repository.PassengerRepository;
import com.rosvitiazev.railways.repository.UserRepository;
import com.rosvitiazev.railways.service.TicketService;
import com.rosvitiazev.railways.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;

    @Override
    public void createUser(User user) {

    }

    @Override
    public Boolean payTicket(Ticket ticket, Long userId) {
        User user = userRepository.findById(userId).orElseThrow((() ->
                new ResourceNotFoundException("User", "id", userId)));
        Double balance = user.getBalance();
        user.setBalance(user.getBalance() - ticket.getPrice());
        if (ticketService.validTicket(ticket) && user.getBalance() >= 0) {
            userRepository.save(user);
            User payUser = userRepository.findById(userId).orElseThrow((() ->
                    new ResourceNotFoundException("User", "id", userId)));
            return ticket.getPrice() == balance - payUser.getBalance();
        }
        return false;
    }

    @Override
    public User getAauthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userRepository.findByEmail(currentUserName).orElseThrow(() ->
                new ResourceNotFoundExceptionByName("user", "name", currentUserName));
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepository.findByLoginOrEmail(principal.getName(), principal.getName()).orElse(new User());
    }
}
