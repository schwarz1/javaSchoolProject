package com.rosvitiazev.railways.service;

import com.rosvitiazev.railways.entity.User;

public interface UserService {
    public void createUser(User user);

    public Boolean payTicket(Double price, Long userId);
}
