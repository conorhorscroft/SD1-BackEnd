package com.example.SlainteFit.model.Health;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {

    List<HealthData> findByUserId(Long userId);

}
