package com.example.SlainteFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.context.ApplicationContext;



/**
 * Main entry point for the SláinteFit Spring Boot application.
 * 
 * - The @SpringBootApplication annotation marks this as a Spring Boot application.
 * - SecurityAutoConfiguration is temporarily excluded to disable authentication for testing.
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SlainteFitApplication {

    public static void main(String[] args) {
        // Initialize the Spring application context
         ApplicationContext ctx = SpringApplication.run(SlainteFitApplication.class, args);

        // Retrieve the RequestMappingHandlerMapping bean to access all registered endpoint mappings and print them for debugging
    ctx.getBean(RequestMappingHandlerMapping.class)
        .getHandlerMethods()
        .forEach((key, value) -> System.out.println("Mapped URL: " + key));
    }

 
}
