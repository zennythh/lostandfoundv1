package com.untilifoundyou.lostandfound.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            System.out.println("User not found: " + username);
            return false;
        }
        
        System.out.println("User found: " + username);
        System.out.println("Stored password: " + user.get().getPassword());
        System.out.println("Provided password: " + password);
    
        if (!user.get().getPassword().equals(password)) {
            System.out.println("Password mismatch!");
            return false;
        }
    
        return true;
    }
}