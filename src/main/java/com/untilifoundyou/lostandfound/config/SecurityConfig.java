package com.untilifoundyou.lostandfound.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF (useful for simple API testing)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // Allow all /auth endpoints
                .anyRequest().authenticated() // Protect other endpoints
            )
            .formLogin().disable() // Disable default login form
            .httpBasic().disable(); // Disable HTTP basic auth
        return http.build();
    }
}
