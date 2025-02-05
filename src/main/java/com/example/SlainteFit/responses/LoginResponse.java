package com.example.SlainteFit.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private long expiresIn;
    private Long userId;
    private String email;
    private String name;
    private Integer age;
    private Float weight;
    private Float height;
    private Integer experience;

    public LoginResponse(String token, long expiresIn, Long userId, String email, 
                         String name, Integer age, Float weight, Float height, Integer experience) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.experience = experience;
    }
}