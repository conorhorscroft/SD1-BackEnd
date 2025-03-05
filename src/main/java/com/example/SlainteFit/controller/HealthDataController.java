package com.example.SlainteFit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SlainteFit.model.Health.HealthData;
import com.example.SlainteFit.service.HealthDataService;

@RestController
@RequestMapping("/api/healthdata")
@CrossOrigin(origins = "*")  // Allow all origins for testing
public class HealthDataController {
    private final HealthDataService healthDataService;

    @Autowired
    public HealthDataController(HealthDataService healthDataService) {
        this.healthDataService = healthDataService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<HealthData> addHealhData(@PathVariable Long userId, @RequestBody HealthData healthData) {
        System.out.println("🚀 Received request to add health data for userId: " + userId);
    System.out.println("Health data: " + healthData);
       
        HealthData addedHealthData = healthDataService.addHealthDataToUser(userId, healthData);
        
        if (addedHealthData != null) {
            return new ResponseEntity<>(addedHealthData, HttpStatus.CREATED); // Return 201 Created
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if user is not found
        }
    }

 @GetMapping("/{userId}")
public ResponseEntity<List<HealthData>> getHealthData(@PathVariable Long userId) {
    List<HealthData> healthDataList = healthDataService.getHealthDataByUserId(userId);

    if (healthDataList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
        return new ResponseEntity<>(healthDataList, HttpStatus.OK);
    }
}

}
