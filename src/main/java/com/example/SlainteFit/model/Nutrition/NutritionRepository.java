package com.example.SlainteFit.model.Nutrition;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends JpaRepository<NutritionData, Long> {


    
}
