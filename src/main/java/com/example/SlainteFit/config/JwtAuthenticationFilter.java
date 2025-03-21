package com.example.SlainteFit.config;

import com.example.SlainteFit.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * JWT Authentication Filter:
 * - A custom security filter that intercepts HTTP requests to validate JWT tokens.
 * - Extends `OncePerRequestFilter` to ensure the filter executes only once per request.
 * - Extracts JWT from the `Authorization` header, validates it, and sets the security context.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor for dependency injection.
     * @param jwtService Service responsible for JWT operations (creation, validation, etc.).
     * @param userDetailsService Service for retrieving user details from the database.
     * @param handlerExceptionResolver Handles security-related exceptions gracefully.
     */
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

      /**
     * This method is executed for each request to check if the JWT token is valid.
     * 
     * - Extracts the token from the `Authorization` header.
     * - Validates the token and retrieves the associated user.
     * - If valid, sets authentication in the `SecurityContext`.
     * 
     * @param request Incoming HTTP request.
     * @param response Outgoing HTTP response.
     * @param filterChain The filter chain to continue request processing.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Extract authorization header from requests
        final String authHeader = request.getHeader("Authorization");

        // Validate header exists and starts with "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extract JWT token
            final String jwt = authHeader.substring(7);

            // Extract username (email)
            final String userEmail = jwtService.extractUsername(jwt);

            // Store current authentication status
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // If username is extracted and no exisiting authentication - proceed
            if (userEmail != null && authentication == null) {

                // Load user details
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // Validate JWT token
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Create authentication token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // Set authentication in the security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Continue with the filter chain
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            // Handle errors with exception resolver
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }

        
    }

    // Exclude certain API endpoints from security filter
    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.startsWith("/auth/signup") || 
           path.startsWith("/auth/login") || 
           path.startsWith("/auth/verify") || 
           path.startsWith("/auth/resend") ||
           path.startsWith("/password/request-reset") || 
           path.startsWith("/password/reset-password");
}


}