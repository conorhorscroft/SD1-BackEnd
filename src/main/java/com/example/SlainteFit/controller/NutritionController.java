package com.example.SlainteFit.controller;

import com.example.SlainteFit.dto.DailyCaloriesDto;
import com.example.SlainteFit.dto.NutritionDataDTO;
import com.example.SlainteFit.model.Nutrition.DailyCalories;
import com.example.SlainteFit.model.Nutrition.DailyCaloriesRepository;
import com.example.SlainteFit.model.Nutrition.NutritionData;
import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.service.NutritionService;
import com.example.SlainteFit.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/nutrition")
@CrossOrigin
public class NutritionController {

    private final DailyCaloriesRepository dailyCaloriesRepository;

    private final NutritionService nutritionService;

    private final UserService userService;

    @Autowired
    public NutritionController(DailyCaloriesRepository dailyCaloriesRepository, NutritionService nutritionService, UserService userService) {
        this.dailyCaloriesRepository = dailyCaloriesRepository;
        this.nutritionService = nutritionService;
        this.userService = userService;
    }


    @DeleteMapping("/delete-nutrition-data/{id}")
    public ResponseEntity<String> deleteNutritionData(@PathVariable Long id) {
        boolean isDeleted = nutritionService.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.ok("Nutrition data deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Nutrition data not found");
        }
    }



    @PostMapping("/save-nutrition-data")
    public ResponseEntity<?> saveNutrition(@RequestBody NutritionDataDTO nutritionDataDTO) {
        List<User> userOptional = userService.getUserById(nutritionDataDTO.getUserId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOptional.get(0);
        NutritionData savedMeal = nutritionService.saveNutritionData(nutritionDataDTO, user);

        return ResponseEntity.ok(savedMeal);
    }

    @PostMapping("/save-daily-calories/{userId}")
    public ResponseEntity<?> saveDailyCalories(@PathVariable Integer userId, @RequestBody DailyCaloriesDto dailyCaloriesDTO) {



        // Check if required fields are present in the request
        if (userId == null || dailyCaloriesDTO.getTotalCalories() == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        // Create new DailyCalories entity from DTO
        DailyCalories dailyCalories = new DailyCalories();
        dailyCalories.setProfileId(userId);
        dailyCalories.setTotalCalories(dailyCaloriesDTO.getTotalCalories());

        try {
            // Save the entity and return the saved record
            DailyCalories savedDailyCalories = dailyCaloriesRepository.save(dailyCalories);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDailyCalories);
        } catch (Exception e) {
            // Log the error and return an error response
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save data: " + e.getMessage());
        }
    }
}
