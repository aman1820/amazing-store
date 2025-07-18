package com.store.amazing.dto.request.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequest(@NotNull Long userId,
                               @NotNull Long productId,
                               @Min(1) Integer quantity) {
}
