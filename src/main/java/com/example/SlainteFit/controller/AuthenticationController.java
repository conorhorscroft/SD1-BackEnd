package com.example.SlainteFit.controller;

import com.example.SlainteFit.dto.LoginUserDto;
import com.example.SlainteFit.dto.RegisterUserDto;
import com.example.SlainteFit.dto.VerifyUserDto;
import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.responses.LoginResponse;
import com.example.SlainteFit.service.AuthenticationService;
import com.example.SlainteFit.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(
            jwtToken, 
            jwtService.getExpirationTime(),
              authenticatedUser.getId(),
        authenticatedUser.getEmail(),
        authenticatedUser.getPhone(),
        authenticatedUser.getName(),
        authenticatedUser.getAge(),
        authenticatedUser.getWeight(),
        authenticatedUser.getHeight(),
        authenticatedUser.getExperience(),
        authenticatedUser.getStrength(),
        authenticatedUser.getEndurance(),
        authenticatedUser.getWeightLoss(),
        authenticatedUser.getHealth(),
        authenticatedUser.getHoursAvailable()
            );
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}