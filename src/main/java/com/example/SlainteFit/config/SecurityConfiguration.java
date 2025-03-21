package com.example.SlainteFit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Security configuration class that sets up authentication and authorization for the application.
 * Configures JWT-based authentication, CORS settings, and URL access rules.
 */

@Configuration
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

     /**
     * Constructor to inject required dependencies.
     * @param jwtAuthenticationFilter Filter to validate JWT tokens in requests
     * @param userDetailsService Service to load user details from the database
     * @param passwordEncoder Encoder used to hash and validate passwords
     */
    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

     /**
     * Configures the security filter chain which defines security rules for HTTP requests.
     * 
     * @param http HttpSecurity object to configure
     * @return The built SecurityFilterChain
     * @throws Exception If an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure CORS (Cross-Origin Resource Sharing) using custom configuration
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Disable CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())
                // Configure authorization rules for HTTP requests, allow unauthorized access to paths listed
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login", "/auth/signup", "/auth/verify", "/auth/resend", "/password/request-reset", "/password/reset-password").permitAll()  // Explicitly allow password reset
                        .requestMatchers("/password/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Configure session management
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Set the authentication provider that will validate credentials
                .authenticationProvider(daoAuthenticationProvider()) // Use DaoAuthenticationProvider
                // Add the JWT filter before the standard authentication filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                

        return http.build();
    }

    // DaoAuthenticationProvider bean handles authentication using UserDetailsService
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // Load user details from the database
        provider.setPasswordEncoder(passwordEncoder); // Set password encoder
        return provider;
    }

    // Configure CORS settings to allow cross-origin requests from certain origins
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://sd1-backend.onrender.com", "http://localhost:8080",
            "http://localhost:8081"  )); // Backend Url and expo web url
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Access-Control-Allow-Origin",       // Added additional headers
            "Access-Control-Allow-Credentials"));
             configuration.setAllowCredentials(true); // Allow credentials
        configuration.setMaxAge(3600L);

        // Apply CORS settings to all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}