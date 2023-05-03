package com.rosvitiazev.railways.config.service;

import com.rosvitiazev.railways.entity.User;
import com.rosvitiazev.railways.payload.dto.LoginDto;
import com.rosvitiazev.railways.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged-in successfully";
    }


    @Override
    public String registration(User registry) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User registrUser = new User();
        registrUser.setPassword(passwordEncoder.encode(registry.getPassword()));
        registrUser.setLogin(registry.getLogin());
        registrUser.setEmail(registry.getEmail());
        registrUser.setBalance(registry.getBalance());
        registrUser.setRole(registry.getRole());
        registrUser.setRole("ROLE_USER");
        userRepository.save(registrUser);
        LoginDto loginDto = new LoginDto(registry.getEmail(), registry.getPassword());
        return login(loginDto);
    }
}

