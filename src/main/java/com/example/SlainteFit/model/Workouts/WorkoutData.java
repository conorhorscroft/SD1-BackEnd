package com.example.SlainteFit.model.Workouts;

import com.example.SlainteFit.model.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

// import JPA package to manage relational data (helps with object-relational mapping)
import jakarta.persistence.*;
import java.time.LocalDate;
// import java.time.Duration;

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
    // private Duration duration;
    private Float distance;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private User user;

    // Getters and Setters
    public Long getWorkoutId() { return workout_id; }
    public void setWorkoutId(Long workout_id) { this.workout_id = workout_id; }

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

    // public Duration getDuration() { return duration; }
    // public void setDuration(Duration duration) { this.duration = duration; }

    public Float getDistance() { return distance; }
    public void setDistance(Float distance) { this.distance = distance; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

