package com.example.SlainteFit.dto;

public class UpdateUserProfileRequest {
    private String name;
    private String email;
    private String phone;
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

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

     public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public Float getWeight() { return weight; }
    public void setWeight(Float weight) { this.weight = weight; }
    
    public Float getHeight() { return height; }
    public void setHeight(Float height) { this.height = height; }
    
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }

    public Integer getStrength() { return strength; }
    public void setStrength(Integer strength) { this.strength = strength; }

    public Integer getEndurance() { return endurance; }
    public void setEndurance(Integer endurance) { this.endurance = endurance; }

    public Integer getWeightLoss() { return weightLoss; }
    public void setWeightLoss(Integer weightLoss) { this.weightLoss = weightLoss; }

    public Integer getHealth() { return health; }
    public void setHealth(Integer health) { this.health = health; }

    public Float getHoursAvailable() { return hoursAvailable; }
    public void setHoursAvailable(Float hoursAvailable) { this.hoursAvailable = hoursAvailable; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; };
}
