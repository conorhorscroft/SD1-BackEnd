package com.example.SlainteFit.model.Workouts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) { 
        this.workoutService = workoutService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<WorkoutData> addWorkout(@PathVariable Long userId, @RequestBody WorkoutData workout) {
        WorkoutData addedWorkout = workoutService.addWorkoutToUser(userId, workout);
        
        if (addedWorkout != null) {
            return new ResponseEntity<>(addedWorkout, HttpStatus.CREATED); // Return 201 Created
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if user is not found
        }
    }

}
