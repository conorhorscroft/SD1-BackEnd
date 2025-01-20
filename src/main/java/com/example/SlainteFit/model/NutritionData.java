package com.example.SlainteFit.model;

import com.example.SlainteFit.model.User.UserData;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

// import JPA package to manage relational data (helps with object-relational mapping)
import jakarta.persistence.*;

@Entity
@Table(name = "nutrition_data")
public class NutritionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutrition_id;

    private LocalDate date;
    private Float calories;
    private Float protein;
    private Float carbs;
    private Float fats;
    private Float hydration;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private UserData user;

    // Getters and Setters
    public Long getId() { return nutrition_id; }
    public void setId(Long nutrition_id) { this.nutrition_id = nutrition_id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Float getCalories() { return calories; }
    public void setCalories(Float calories) { this.calories = calories; }

    public Float getProtein() { return protein; }
    public void setProtein(Float protein) { this.protein = protein; }

    public Float getCarbs() { return carbs; }
    public void setCarbs(Float carbs) { this.carbs = carbs; }

    public Float getFats() { return fats; }
    public void setFats(Float fats) { this.fats = fats; }

    public Float getHydration() { return hydration; }
    public void setHydration(Float hydration) { this.hydration = hydration; }

    public UserData getUser() { return user; }
    public void setUser(UserData user) { this.user = user; }

}
