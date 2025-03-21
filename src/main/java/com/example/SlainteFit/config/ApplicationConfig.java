package com.example.SlainteFit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.SlainteFit.model.User.UserRepository;

/**
 * Configuration class for authentication and security-related beans.
 * 
 * - Registers beans for user authentication, password encoding, and authentication management.
 * - Uses Spring Security's DAO-based authentication with a custom UserDetailsService.
 */
@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    // Constructor injection of UserRepository to allow retrieval of user details for authentication
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetches user details from respoistory and throws an exception if not found
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    // Password encoder bean to securely hash passwords before saving
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager bean for handling authentication requests
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // AuthenticationProvider bean for DAO-based authentication
    @Bean
    AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService()); // Loads user details
        authProvider.setPasswordEncoder(passwordEncoder()); // Hashes passwords for comparison

        return authProvider;
    }

}
