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
    public String registrationAdmin(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User registrUser = new User();
        registrUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registrUser.setLogin(user.getLogin());
        registrUser.setEmail(user.getEmail());
        registrUser.setBalance(user.getBalance());
        registrUser.setRole(user.getRole());
        registrUser.setRole("ROLE_ADMIN");
        userRepository.save(registrUser);
        LoginDto loginDto = new LoginDto(user.getEmail(),user.getPassword());
        return login(loginDto);
    }


    @Override
    public String registration(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User registrUser = new User();
        registrUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registrUser.setLogin(user.getLogin());
        registrUser.setEmail(user.getEmail());
        registrUser.setBalance(user.getBalance());
        registrUser.setRole(user.getRole());
        registrUser.setRole("ROLE_USER");
        userRepository.save(registrUser);
        LoginDto loginDto = new LoginDto(user.getEmail(),user.getPassword());
        return login(loginDto);
    }
}
