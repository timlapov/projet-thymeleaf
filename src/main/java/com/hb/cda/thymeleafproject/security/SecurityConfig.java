package com.hb.cda.thymeleafproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configures the security settings for the application.
@Configuration
public class SecurityConfig {

    // Configures the security filter chain.
    @Bean
    SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
        .anyRequest().permitAll());
        http.formLogin(login -> login.loginPage("/login").failureUrl("/login?error=true"));
        
        return http.build();
    }

    // Configures the password encoder.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

