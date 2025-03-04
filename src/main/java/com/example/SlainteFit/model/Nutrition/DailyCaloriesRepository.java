package com.example.SlainteFit.model.Nutrition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DailyCaloriesRepository extends JpaRepository<DailyCalories, Long> {

    Optional<DailyCalories> findByProfileIdAndDateValue(Integer profileId, LocalDateTime dateValue);

    // used for future queries 
}
