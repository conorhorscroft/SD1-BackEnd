package com.example.SlainteFit.dto;

public class UpdateUserProfileRequest {
    private String name;
    private String email;
    private Integer age;
    private Float weight;
    private Float height;
    private Integer experience;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public Float getWeight() { return weight; }
    public void setWeight(Float weight) { this.weight = weight; }
    
    public Float getHeight() { return height; }
    public void setHeight(Float height) { this.height = height; }
    
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }
}
