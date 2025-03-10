package com.example.SlainteFit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlainteFit.model.Health.HealthData;
import com.example.SlainteFit.model.Health.HealthDataRepository;
import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.model.User.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class HealthDataService {

    private final UserRepository userRepository;
    private final HealthDataRepository healthDataRepository;

    @Autowired
    public HealthDataService(UserRepository userRepository, HealthDataRepository healthDataRepository) {
        this.userRepository = userRepository;
        this.healthDataRepository = healthDataRepository;
    }

    @Transactional
    public HealthData addHealthDataToUser(Long userId, HealthData healthData) {
         Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            healthData.setUser(user); // assign a user to health data
            HealthData savedHealthData= healthDataRepository.save(healthData);
            return savedHealthData;
        }
        return null;
    }

    public List<HealthData> getHealthDataByUserId(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);

          if (userOpt.isPresent()) {
            return healthDataRepository.findByUserId(userId);
        }
        return List.of(); // return empty list if user not found

    }

}
