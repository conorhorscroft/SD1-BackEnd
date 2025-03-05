package com.example.SlainteFit.model.Health;

import java.time.LocalDate;

import com.example.SlainteFit.model.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "health_data")
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long health_id;

    private LocalDate date;
    private Integer mood;
    private Integer hoursOfSleep;
    private Integer screenTime;
    private boolean timeOutdoors;


    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private User user;

    public Long getHealth_id() {
        return health_id;
    }

    public void setHealth_id(Long health_id) {
        this.health_id = health_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMood() {
        return mood;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public Integer getHoursOfSleep() {
        return hoursOfSleep;
    }

    public void setHoursOfSleep(Integer hoursOfSleep) {
        this.hoursOfSleep = hoursOfSleep;
    }

    public Integer getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(Integer screenTime) {
        this.screenTime = screenTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isTimeOutdoors() {
        return timeOutdoors;
    }

    public void setTimeOutdoors(boolean timeOutdoors) {
        this.timeOutdoors = timeOutdoors;
    }

    


}
