package com.example.SlainteFit.model.Nutrition;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface DailyCaloriesRepository extends JpaRepository<DailyCalories, Long> {


//    @Query("SELECT d FROM DailyCalories d WHERE d.profileId = :profileId AND DATE(d.dateValue) = :date")
//    Optional<DailyCalories> findByProfileIdAndDate(@Param("profileId") Integer profileId, @Param("date") LocalDate date);


    @Query("SELECT d FROM DailyCalories d WHERE d.profileId = :profileId AND FUNCTION('DATE', d.dateValue) = :date")
    Optional<DailyCalories> findByProfileIdAndDate(@Param("profileId") Integer profileId, @Param("date") LocalDate date);


    
}

