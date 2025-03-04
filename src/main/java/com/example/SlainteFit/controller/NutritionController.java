package com.example.SlainteFit.controller;

import com.example.SlainteFit.dto.DailyCaloriesDto;
import com.example.SlainteFit.model.Nutrition.DailyCalories;
import com.example.SlainteFit.model.Nutrition.DailyCaloriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/nutrition")
@CrossOrigin
public class NutritionController {

    private final DailyCaloriesRepository dailyCaloriesRepository;

    @Autowired
    public NutritionController(DailyCaloriesRepository dailyCaloriesRepository) {
        this.dailyCaloriesRepository = dailyCaloriesRepository;
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
