package com.store.amazing.dto.request.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProduct(@NotNull String name,
                            String description,
                            @NotNull BigDecimal price,
                            @NotNull Integer stock,
                            @NotNull Long categoryId) {

    // Additional validation or transformation methods can be added here if needed
}
