package com.example.SlainteFit.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

    @SuppressWarnings("null")
    void deleteById(Long id);
    Optional<UserData> findById(long id);

}

