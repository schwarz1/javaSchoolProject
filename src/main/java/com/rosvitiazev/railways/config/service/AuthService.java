package com.rosvitiazev.railways.config.service;

import com.rosvitiazev.railways.entity.User;
import com.rosvitiazev.railways.payload.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String registrationAdmin(User user);

    String registration(User registr);
}
