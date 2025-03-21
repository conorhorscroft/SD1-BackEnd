package com.example.SlainteFit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration class for setting up email sending using JavaMailSender.
 * 
 * - Configures SMTP settings for sending emails
 * - Uses Gmail's SMTP server 
 */

@Configuration
public class EmailConfiguration {

    // Injects email log-in details from application.properties through environment variables
    // Prevents hardcoding of sensitive info
    @Value("${spring.mail.username}")
    private String emailUsername;

    @Value("${spring.mail.password}")
    private String emailPassword;

    // Configures a JavaMailSender bean with details as below
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Server address
        mailSender.setPort(587); // Port number
        mailSender.setUsername(emailUsername); // Injected username
        mailSender.setPassword(emailPassword); // Injected password

        // Configure additional properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}