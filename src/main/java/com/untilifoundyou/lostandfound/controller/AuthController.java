package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.enums.UserRole;
import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import com.untilifoundyou.lostandfound.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, GuestUserService guestUserService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.guestUserService = guestUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Check if username exists and is valid
        User user = userRepository.findByUsername(username).orElse(null);

        // If the user is valid and the password matches
        if (user != null && user.getPassword().equals(password)) {
            // Only proceed if the user has a role of "user" (exclude admins)
            if (UserRole.User.equals(user.getRole())) {
                // Generate JWT token
                String token = jwtUtil.generateToken(user.getUsername(), user.getId());

                // Return response with the token
                return ResponseEntity.ok(Map.of("message", "Login successful", "token", token));
            } else {
                // If the user is an admin, return a forbidden error
                return ResponseEntity.status(403).body("Access denied: Admin users cannot log in here.");
            }
        }

        // If the user is not found or credentials don't match, log in as guest
        User guestUser = guestUserService.getGuestUser();  // Get the guest user from the database
        String token = jwtUtil.generateToken(guestUser.getUsername(), guestUser.getId());

        return ResponseEntity.ok(Map.of("message", "Logged in as guest: " + guestUser.getUsername(), "token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }

}