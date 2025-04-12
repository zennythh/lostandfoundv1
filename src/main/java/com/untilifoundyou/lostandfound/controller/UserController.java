package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import com.untilifoundyou.lostandfound.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean success = userService.register(user.getUsername(), user.getPassword(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getContactNum(), user.getEmail(), user.getRole());
        if (success) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
    }
}
