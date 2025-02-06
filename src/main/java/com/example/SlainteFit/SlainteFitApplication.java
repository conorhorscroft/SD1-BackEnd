package com.example.SlainteFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.context.ApplicationContext;



// Temporarily include this exclude for testing (remove authentication)
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SlainteFitApplication {

    public static void main(String[] args) {
         ApplicationContext ctx = SpringApplication.run(SlainteFitApplication.class, args);

        // Print all mapped endpoints
    ctx.getBean(RequestMappingHandlerMapping.class)
        .getHandlerMethods()
        .forEach((key, value) -> System.out.println("Mapped URL: " + key));
    }

 
}
