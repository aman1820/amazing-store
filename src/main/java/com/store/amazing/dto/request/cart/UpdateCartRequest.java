package com.store.amazing.dto.request.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCartRequest(@NotNull Long userId, @NotNull Long cartItemId, @Min(1) Integer quantity) {
}
