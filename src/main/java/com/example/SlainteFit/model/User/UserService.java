package com.example.SlainteFit.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByEmail (String email) {
        return userRepository.findAll().stream().filter(user -> user.getEmail().toLowerCase().equals(email)).collect(Collectors.toList());
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User updateUser(User updatedUser) {
        Optional<User> existingUser = userRepository.findById(updatedUser.getId());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setId(updatedUser.getId());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setAge(updatedUser.getAge());
            userToUpdate.setHeight(updatedUser.getHeight());
            userToUpdate.setWeight(updatedUser.getWeight());
            userToUpdate.setNutritionLogs(updatedUser.getNutritionLogs());
            userToUpdate.setWorkouts(updatedUser.getWorkouts());

            userRepository.save(userToUpdate);
            return userToUpdate;
            
        }
        return null;
    }
    @Transactional // ensure operation is part of a transaction (maintain data integrity)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
