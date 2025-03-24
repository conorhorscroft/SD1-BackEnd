package com.example.SlainteFit.model.Nutrition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends JpaRepository<NutritionData, Long> {


    @Query("SELECT n FROM NutritionData n WHERE n.user.id = :userId AND n.date = :date")
    List<NutritionData> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

}

