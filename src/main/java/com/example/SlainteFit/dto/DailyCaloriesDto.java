package com.example.SlainteFit.dto;
import java.time.LocalDateTime;


public class DailyCaloriesDto {

    private Integer profileId;
    private Integer totalCalories;

    // Getters and Setters

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Integer totalCalories) {
        this.totalCalories = totalCalories;
    }

    @Override
    public String toString() {
        return "DailyCaloriesDto{" +
                "profileId=" + profileId +
                ", totalCalories=" + totalCalories +
                '}';
    }
}


