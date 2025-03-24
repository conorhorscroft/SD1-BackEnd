package com.example.SlainteFit.model.Nutrition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "daily_calories")
public class DailyCalories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;

    @Column(name = "total_calories", nullable = false)
    private Integer totalCalories;

    @Column(name = "date_value", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateValue;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDateValue() {
        return dateValue;
    }

    public void setDateValue(LocalDateTime dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return "DailyCalories{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", totalCalories=" + totalCalories +
                ", dateValue=" + dateValue +
                '}';
    }
}
