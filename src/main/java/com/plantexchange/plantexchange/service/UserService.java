package com.plantexchange.plantexchange.service;

import com.plantexchange.plantexchange.model.User;
import com.plantexchange.plantexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerNewUser(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Account with this email was already registered");
        }
        return userRepository.save(new User(email, passwordEncoder.encode(password)));
    }
}
