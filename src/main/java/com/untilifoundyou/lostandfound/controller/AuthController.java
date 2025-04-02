package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import com.untilifoundyou.lostandfound.security.GuestUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final GuestUserService guestUserService;

    public AuthController(UserRepository userRepository, GuestUserService guestUserService) {
        this.userRepository = userRepository;
        this.guestUserService = guestUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Check if username is valid (no need for Spring Security)
        User user = userRepository.findByUsername(username).orElse(null);

        // If the user is valid and the password matches (without encryption for now)
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful");
        }

        // If the user is not found or credentials don't match, return the guest user
        User guestUser = guestUserService.getGuestUser();  // Get the guest user from the database
        return ResponseEntity.ok("Logged in as guest: " + guestUser.getUsername());
    }
}
