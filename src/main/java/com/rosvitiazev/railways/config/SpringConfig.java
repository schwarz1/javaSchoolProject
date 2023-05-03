package com.rosvitiazev.railways.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET,
                                        "/login",
                                        "/login-user",
                                        "/registration",
                                        "/registration-user",
                                        "/start/schedules",
                                        "/start/schedules/departureStation/arrivalStation",
                                        "/start/schedules/date").permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/login-user",
                                        "/registration-user").permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        "/admin/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,
                                        "/admin/**").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated())
                .formLogin(formLogin ->
                        formLogin
                                .loginProcessingUrl("/login-user")
                                .defaultSuccessUrl("/schedules", true)
                                .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()).logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/start/schedules")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                });
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}