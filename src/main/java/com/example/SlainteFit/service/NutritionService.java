package com.example.SlainteFit.service;

import com.example.SlainteFit.dto.NutritionDataDTO;
import com.example.SlainteFit.model.Nutrition.NutritionData;
import com.example.SlainteFit.model.Nutrition.NutritionRepository;
import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.model.User.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NutritionService {

    private static final Logger logger = LoggerFactory.getLogger(NutritionService.class);

    private final NutritionRepository nutritionRepository;

    private final UserRepository userRepository;

    @Autowired
    public NutritionService(NutritionRepository nutritionRepository, UserRepository userRepository) {
        this.nutritionRepository = nutritionRepository;
        this.userRepository = userRepository;
    }

    public NutritionData saveNutritionData(NutritionDataDTO dto, User user) {
        logger.info("Saving Nutrition Data...");
        logger.info("DTO Received: Date={}, Calories={}, Protein={}, Carbs={}, Fats={}, Hydration={}, UserID={}",
                dto.getDate(), dto.getCalories(), dto.getProtein(), dto.getCarbs(), dto.getFats(), dto.getHydration(), dto.getUserId());

        if (user != null) {
            logger.info("User Details: ID={}, Name={}, Email={}", user.getId(), user.getName(), user.getEmail());
        } else {
            logger.warn("User is null! This might cause a failure.");
        }

        NutritionData nutritionData = new NutritionData();
        nutritionData.setDate(dto.getDate());
        nutritionData.setCalories(dto.getCalories());
        nutritionData.setProtein(dto.getProtein());
        nutritionData.setCarbs(dto.getCarbs());
        nutritionData.setFats(dto.getFats());
        nutritionData.setHydration(dto.getHydration());
        nutritionData.setUser(user);

        NutritionData savedData = nutritionRepository.save(nutritionData);

        logger.info("Nutrition Data Saved: ID={}, Date={}, Calories={}, Protein={}, Carbs={}, Fats={}, Hydration={}, UserID={}",
                savedData.getId(), savedData.getDate(), savedData.getCalories(), savedData.getProtein(),
                savedData.getCarbs(), savedData.getFats(), savedData.getHydration(), savedData.getUser().getId());

        return savedData;
    }
    public boolean deleteById(Long id) {
        logger.info("Attempting to delete Nutrition Data with ID={}", id);

        Optional<NutritionData> nutritionDataOptional = nutritionRepository.findById(id);
        if (nutritionDataOptional.isPresent()) {
            logger.info("Nutrition Data Found: ID={}, Date={}, Calories={}, Protein={}, Carbs={}, Fats={}, Hydration={}, UserID={}",
                    nutritionDataOptional.get().getId(),
                    nutritionDataOptional.get().getDate(),
                    nutritionDataOptional.get().getCalories(),
                    nutritionDataOptional.get().getProtein(),
                    nutritionDataOptional.get().getCarbs(),
                    nutritionDataOptional.get().getFats(),
                    nutritionDataOptional.get().getHydration(),
                    nutritionDataOptional.get().getUser().getId());

            nutritionRepository.deleteById(id);
            logger.info("Successfully deleted Nutrition Data with ID={}", id);
            return true;
        } else {
            logger.warn("Nutrition Data with ID={} not found. Deletion failed.", id);
            return false;
        }
    }
}
