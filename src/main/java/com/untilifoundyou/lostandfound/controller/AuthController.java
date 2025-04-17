package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.enums.UserRole;
import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import com.untilifoundyou.lostandfound.security.JwtUtil;
import com.untilifoundyou.lostandfound.security.GuestUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,  JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Look for the user by username
        User user = userRepository.findByUsername(username).orElse(null);

        // Validate credentials
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username or password.");
        }

        // Generate token for valid user
        String token = jwtUtil.generateToken(user.getUsername(), user.getId());

        // Return login success response with role
        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", token,
                "role", user.getRole().toString()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }

}