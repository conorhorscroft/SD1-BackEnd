package com.example.SlainteFit.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SlainteFit.dto.UpdateUserProfileRequest;
import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.model.User.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
}


    public List<User> getUserById(Long id) {
    return userRepository.findById(id)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
}

    public User addUser(User user) {
        // Hash password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Save User to repository
        userRepository.save(user);
        return user;
    }

    // public User updateUser(User updatedUser) {
    //     Optional<User> existingUser = userRepository.findById(updatedUser.getId());

    //     if (existingUser.isPresent()) {
    //         User userToUpdate = existingUser.get();
    //         userToUpdate.setId(updatedUser.getId());
    //         userToUpdate.setEmail(updatedUser.getEmail());
    //         userToUpdate.setAge(updatedUser.getAge());
    //         userToUpdate.setHeight(updatedUser.getHeight());
    //         userToUpdate.setWeight(updatedUser.getWeight());
    //         userToUpdate.setPhone(updatedUser.getPhone());
    //         userToUpdate.setExperience(updatedUser.getExperience());
    //         userToUpdate.setNutritionData(updatedUser.getNutritionData());
    //         userToUpdate.setWorkouts(updatedUser.getWorkouts());

    //         if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
    //             String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
    //             userToUpdate.setPassword(hashedPassword); // Set the hashed password to the user object
    //         }

    //         userRepository.save(userToUpdate);
    //         return userToUpdate;
            
    //     }
    //     return null;
    // }
    public User updateUserProfile(Long userId, UpdateUserProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        // Update only the fields that are provided in the request
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
          if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        if (request.getWeight() != null) {
            user.setWeight(request.getWeight());
        }
        if (request.getHeight() != null) {
            user.setHeight(request.getHeight());
        }
        if (request.getExperience() != null) {
            user.setExperience(request.getExperience());
        }
          if (request.getStrength() != null) {
            user.setStrength(request.getStrength());
        }
          if (request.getEndurance() != null) {
            user.setEndurance(request.getEndurance());
        }
          if (request.getWeightLoss() != null) {
            user.setWeightLoss(request.getWeightLoss());
        }
          if (request.getHealth() != null) {
            user.setHealth(request.getHealth());
        }
          if (request.getHoursAvailable() != null) {
            user.setHoursAvailable(request.getHoursAvailable());
        }

        return userRepository.save(user);
    }



    @Transactional // ensure operation is part of a transaction (maintain data integrity)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
