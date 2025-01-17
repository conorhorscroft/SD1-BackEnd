package com.example.SlainteFit.model.User;

import java.util.ArrayList;
import java.util.List;

import com.example.SlainteFit.model.NutritionLog;
import com.example.SlainteFit.model.Workout;

import jakarta.persistence.*; // Import JPA package

@Entity
@Table(name = "user_data")
public class User {

    @Id // Indicates this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates IDs
    private Long id;

    private String name;
    private String email;
    private Integer age;
    private Float weight;
    private Float height;

  

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workout> workouts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NutritionLog> nutritionLogs = new ArrayList<>();

    // Default constructor (required by JPA)
    public User() {}

    // Parameterized constructor
    public User(Long id, String name, String email, Integer age, Float weight, Float height) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public List<Workout> getWorkouts() { return workouts; }
    public void setWorkouts(List<Workout> workouts) { this.workouts = workouts; }

    public List<NutritionLog> getNutritionLogs() { return nutritionLogs; }
    public void setNutritionLogs(List<NutritionLog> nutritionLogs) { this.nutritionLogs = nutritionLogs; }
}
