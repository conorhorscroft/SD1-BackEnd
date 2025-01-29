package com.example.SlainteFit.model.Authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SlainteFit.model.User.UserData;
import com.example.SlainteFit.model.User.UserRepository;



import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;  // Injecting BCryptPasswordEncoder or other encoder
    }

    /**
     * Authenticate a user by comparing the entered password with the hashed password stored in the database.
     * 
     * @param email      User's email for login.
     * @param rawPassword User's raw password to authenticate.
     * @return boolean    True if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String email, String rawPassword) {
        // Find user by email
        Optional<UserData> userOpt = userRepository.findByEmail(email);

        // If the user exists, check if the password matches
        if (userOpt.isPresent()) {
            UserData user = userOpt.get();
            // Compare the raw password with the stored hashed password
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        // If the user does not exist, return false
        return false;
    }
}
