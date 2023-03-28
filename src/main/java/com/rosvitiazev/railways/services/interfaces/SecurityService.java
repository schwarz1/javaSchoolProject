package com.rosvitiazev.railways.services.interfaces;

public interface SecurityService {
    String findLoggedInEmail();

    void autologin(String email, String password);
}
