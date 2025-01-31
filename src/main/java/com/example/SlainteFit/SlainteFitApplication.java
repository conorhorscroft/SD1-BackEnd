package com.example.SlainteFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



// Temporarily include this exclude for testing (remove authentication)
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SlainteFitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlainteFitApplication.class, args);
    }

 
}
