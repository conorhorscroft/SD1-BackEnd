package com.example.SlainteFit.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @SuppressWarnings("null")
    void deleteById(Long id);
    List<User> findById(long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationCode(String verificationCode);

}

