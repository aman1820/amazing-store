package com.store.amazing.service;

import com.store.amazing.dto.request.auth.RegisterRequest;
import com.store.amazing.dto.response.auth.RegisterResponse;
import com.store.amazing.entity.User;
import com.store.amazing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        var user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole("USER");

        userRepository.save(user);
        return new RegisterResponse("User registered successfully", user.getId());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}