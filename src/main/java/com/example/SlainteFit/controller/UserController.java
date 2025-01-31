package com.example.SlainteFit.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SlainteFit.model.User.User;
import com.example.SlainteFit.service.UserService;



@RestController
@RequestMapping(path = "api/user")
@CrossOrigin // allow cross-origin requests
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers( 
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) Integer age,
        @RequestParam(required = false) Float weight,
        @RequestParam(required = false) Float height) {

            if (id != null) {
                return userService.getUserById(id);
            }
            if (email != null) {
        return userService.getUserByEmail(email);
    }
    return userService.getUsers();

        }
    @PostMapping
    public ResponseEntity<User> addUserEntity(@RequestBody User user) {
        
        
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping 
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User resultUser = userService.updateUser(user);
        if (resultUser != null) {
            return new ResponseEntity<>(resultUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
