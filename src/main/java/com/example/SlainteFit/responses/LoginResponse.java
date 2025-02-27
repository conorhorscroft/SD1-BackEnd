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
    private String phone;
    private String name;
    private Integer age;
    private Float weight;
    private Float height;
    private Integer experience;
    private Integer strength;
    private Integer endurance;
    private Integer weightLoss;
    private Integer health;
    private Float hoursAvailable;
    private String gender;

    public LoginResponse(String token, long expiresIn, Long userId, String email, String phone, 
                         String name, Integer age, Float weight, Float height, Integer experience, Integer strength, Integer endurance, Integer weightLoss, Integer health, Float hoursAvailable, String gender) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.experience = experience;
        this.strength = strength;
        this.endurance = endurance;
        this.weightLoss = weightLoss;
        this.health = health;
        this.hoursAvailable = hoursAvailable;
        this.gender = gender;
    }
}