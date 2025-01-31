package com.example.SlainteFit.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.SlainteFit.model.Nutrition.NutritionData;
import com.example.SlainteFit.model.Workouts.WorkoutData;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*; // Import JPA package
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user_data")
public class User implements UserDetails {

    @Id // Indicates this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates IDs
    @Column(name = "id")
    private Long id;

     @Column(unique = true, nullable = false)
    private String email;


    private String name;
    private Integer age;
    private Float weight;
    private Float height;
    private String phone;
    private Integer experience;


    @Column(nullable = false)
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;
    private boolean enabled;
    

  

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WorkoutData> workouts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NutritionData> nutritionData = new ArrayList<>();

    // Default constructor (required by JPA) - lombok is now handling
    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Parameterized constructor
    public User(Long id, String name, String email, Integer age, Float weight, Float height, String phone, Integer experience) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.phone = phone;
        this.experience = experience;
    }

        // Constructor without id ( as auto-generated )
       public User(String name, String email, Integer age, Float weight, String password, Float height, String phone, Integer experience) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.password = password;
        this.phone = phone;
        this.experience = experience;
    }

    

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Float getWeight() { return weight; }
    public void setWeight(Float weight) { this.weight = weight; }

    public Float getHeight() { return height; }
    public void setHeight(Float height) { this.height = height; }

     public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

     public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }

    public String getPassword() { return password; }
    public void setPassword (String password) { this.password = password; }

    public List<WorkoutData> getWorkouts() { return workouts; }
    public void setWorkouts(List<WorkoutData> workouts) { this.workouts = workouts; }

    public List<NutritionData> getNutritionData() { return nutritionData; }
    public void setNutritionData(List<NutritionData> nutritionData) { this.nutritionData = nutritionData; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return email;
        // throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }



}
