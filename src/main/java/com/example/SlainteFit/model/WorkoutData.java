package com.example.SlainteFit.model;

import com.example.SlainteFit.model.User.UserData;

// import JPA package to manage relational data (helps with object-relational mapping)
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workout_data")
public class WorkoutData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_id;

    private String exerciseName;
    private LocalDate date;
    private Integer sets;
    private Integer reps;
    private Float weight;
    // private Float duration;
    private Float distance;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private UserData user;

    // Getters and Setters
    public Long getId() { return workout_id; }
    public void setId(Long workout_id) { this.workout_id = workout_id; }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getSets() { return sets; }
    public void setSets(Integer sets) { this.sets = sets; }

    public Integer getReps() { return reps; }
    public void setReps(Integer reps) { this.reps = reps; }

    public Float getWeight() { return weight; }
    public void setWeight(Float weight) { this.weight = weight; }

    // public Float getDuration() { return duration; }
    // public void setDuration(Float duration) { this.duration = duration; }

    public Float getDistance() { return distance; }
    public void setDistance(Float distance) { this.distance = distance; }

    public UserData getUser() { return user; }
    public void setUser(UserData user) { this.user = user; }
}

