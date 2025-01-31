package com.example.SlainteFit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String email;
    private String password;
    private String name;
    private Integer age;
    private Float weight;
    private Float height;
    private String phone;
    private Integer experience;


}