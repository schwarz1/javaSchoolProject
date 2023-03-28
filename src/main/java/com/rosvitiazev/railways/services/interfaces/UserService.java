package com.rosvitiazev.railways.services.interfaces;

import com.rosvitiazev.railways.domain.entities.User;

public interface UserService {
    void save(User user);
    User findByEmail(String email);
}
