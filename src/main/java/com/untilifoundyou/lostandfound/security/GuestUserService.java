package com.untilifoundyou.lostandfound.security;

import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestUserService {

    private final UserRepository userRepository;

    public GuestUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Returns the guest user if no authenticated user is available
    public User getGuestUser() {
        return userRepository.findByUsername("guest")
                .orElseThrow(() -> new RuntimeException("Guest user not found in the database"));
    }
}
