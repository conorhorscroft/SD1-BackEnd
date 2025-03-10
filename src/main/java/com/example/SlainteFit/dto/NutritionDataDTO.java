package com.example.SlainteFit.dto;

import java.time.LocalDate;

public class NutritionDataDTO {
    private LocalDate date;
    private Float calories;
    private Float protein;
    private Float carbs;
    private Float fats;
    private Float hydration;
    private Long userId;

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

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}