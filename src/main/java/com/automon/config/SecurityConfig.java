package com.automon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws-metrics/**").permitAll() // Allow WebSockets
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF for WebSockets
                .formLogin(login -> login.disable()) // Disable default login form
                .httpBasic(basic -> basic.disable()); // Disable HTTP Basic Auth

        return http.build();
    }
}

