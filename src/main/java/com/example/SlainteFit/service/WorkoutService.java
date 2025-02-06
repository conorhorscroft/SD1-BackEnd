package com.example.SlainteFit.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.model.User.UserRepository;
import com.example.SlainteFit.model.Workouts.WorkoutData;
import com.example.SlainteFit.model.Workouts.WorkoutRepository;

import jakarta.transaction.Transactional;


@Service
public class WorkoutService {

    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;

     @Autowired
    public WorkoutService(UserRepository userRepository, WorkoutRepository workoutRepository) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
    }

    @Transactional
    public WorkoutData addWorkoutToUser(Long userId, WorkoutData workout) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            workout.setUser(user); // assign a user to a workout
            WorkoutData savedWorkout = workoutRepository.save(workout);
            return savedWorkout;
        }
        return null;
    }

   

}
