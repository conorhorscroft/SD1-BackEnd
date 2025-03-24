package com.example.SlainteFit.model.Nutrition;

import com.example.SlainteFit.model.User.User;
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

    @Column(name = "meal_name")
    private String mealName;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private User user;

    // Getters and Setters
    public Long getId() { return nutrition_id; }
    public void setId(Long nutrition_id) { this.nutrition_id = nutrition_id; }

    public Long getNutrition_id() {
        return nutrition_id;
    }

    public void setNutrition_id(Long nutrition_id) {
        this.nutrition_id = nutrition_id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

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

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}
