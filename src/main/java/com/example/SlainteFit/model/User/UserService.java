package com.example.SlainteFit.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



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

    public List<UserData> getUsers() {
        return userRepository.findAll();
    }

    public List<UserData> getUserByEmail(String email) {
    return userRepository.findByEmail(email)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
}


    public List<UserData> getUserById(Long id) {
    return userRepository.findById(id)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
}

    public UserData addUser(UserData user) {
        userRepository.save(user);
        return user;
    }

    public UserData updateUser(UserData updatedUser) {
        Optional<UserData> existingUser = userRepository.findById(updatedUser.getId());

        if (existingUser.isPresent()) {
            UserData userToUpdate = existingUser.get();
            userToUpdate.setId(updatedUser.getId());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setAge(updatedUser.getAge());
            userToUpdate.setHeight(updatedUser.getHeight());
            userToUpdate.setWeight(updatedUser.getWeight());
            userToUpdate.setNutritionData(updatedUser.getNutritionData());
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
