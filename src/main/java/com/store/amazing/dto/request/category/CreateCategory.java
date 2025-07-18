package com.store.amazing.dto.request.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategory(@NotBlank String name) {
}
