package com.rosvitiazev.railways.controller;

import com.rosvitiazev.railways.config.service.AuthService;
import com.rosvitiazev.railways.entity.User;
import com.rosvitiazev.railways.payload.dto.LoginDto;
import com.rosvitiazev.railways.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login/login-user";
    }

    @PostMapping("/login-user")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, Model model) {
        try {
            authService.login(loginDto);
            return "redirect:/schedules";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login/login-user";
        }
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "login/registration-user";
    }

    @PostMapping("/registration-user")
    public String registration(@ModelAttribute("user") User user, Model model) {
        try {
            String response = authService.registration(user);
            model.addAttribute("successMessage", response);
            userService.createUser(user);
            authService.login(new LoginDto(user.getEmail(), user.getPassword()));
            return "redirect:/schedules";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error occurred while registering user");
            return "login/registration-user";
        }
    }
}


