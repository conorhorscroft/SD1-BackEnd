package com.example.SlainteFit.model.Workouts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlainteFit.model.User.UserData;
import com.example.SlainteFit.model.User.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class WorkoutService {

    private final UserRepository userRepository;

     @Autowired
    public WorkoutService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public WorkoutData addWorkoutToUser(Long userId, WorkoutData workout) {
        Optional<UserData> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            UserData user = userOpt.get();
            user.getWorkouts().add(workout);
            userRepository.save(user);
            return workout;
        }
        return null;
    }

   

}
