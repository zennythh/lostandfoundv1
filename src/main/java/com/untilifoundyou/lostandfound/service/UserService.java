package com.untilifoundyou.lostandfound.service;

import com.untilifoundyou.lostandfound.enums.UserRole;
import com.untilifoundyou.lostandfound.model.User;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // REGISTER METHOD
    public boolean register(String username, String password, String first_name, String middle_name, String last_name, String contactNum, String email, UserRole role) {
        if (userRepository.existsByUsername(username)) {
            return false; // username already taken
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(first_name);
        newUser.setMiddleName(middle_name);
        newUser.setLastName(last_name);
        newUser.setContactNum(contactNum);
        newUser.setEmail(email);
        newUser.setRole(role);

        userRepository.save(newUser); // triggers insert, not merge
        return true;
    }
}

