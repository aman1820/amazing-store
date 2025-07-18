package com.store.amazing.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@Email(message = "Should be valid Email") String email,
                           @NotBlank(message = "password cannot be blank") String password) {
}
