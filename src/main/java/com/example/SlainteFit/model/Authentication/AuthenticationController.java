// package com.example.SlainteFit.model.Authentication;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthenticationController {

//     private final AuthenticationService authenticationService;

//     @Autowired
//     public AuthenticationController(AuthenticationService authenticationService) {
//         this.authenticationService = authenticationService;
//     }

//     /**
//      * Login endpoint for user authentication.
//      * 
//      * @param email       User's email.
//      * @param password    User's raw password.
//      * @return String     Success or failure message.
//      */
//     @PostMapping("/login")
//     public String login(@RequestParam String email, @RequestParam String password) {
//         boolean isAuthenticated = authenticationService.authenticateUser(email, password);
//         if (isAuthenticated) {
//             return "Login successful!";
//         } else {
//             return "Invalid email or password.";
//         }
//     }
// }
