package com.example.SlainteFit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SlainteFit.model.Workouts.WorkoutData;
import com.example.SlainteFit.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "*")  // Allow all origins for testing
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) { 
        this.workoutService = workoutService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<WorkoutData> addWorkout(@PathVariable Long userId, @RequestBody WorkoutData workout) {
        System.out.println("🚀 Received request to add workout for userId: " + userId);
    System.out.println("Workout data: " + workout);
       
        WorkoutData addedWorkout = workoutService.addWorkoutToUser(userId, workout);
        
        if (addedWorkout != null) {
            return new ResponseEntity<>(addedWorkout, HttpStatus.CREATED); // Return 201 Created
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if user is not found
        }
    }

}
