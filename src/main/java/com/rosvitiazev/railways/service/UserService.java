package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.entity.Ticket;
import com.rosvitiazev.railways.entity.User;

import java.security.Principal;

public interface UserService {
    void createUser(User user);

    Boolean payTicket(Ticket ticket, Long userId);

    User getAauthenticationUser();


    User getUserByPrincipal(Principal principal);
}
