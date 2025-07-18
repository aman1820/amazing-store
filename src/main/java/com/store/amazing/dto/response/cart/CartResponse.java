package com.store.amazing.dto.response.cart;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CartResponse(Long id, Long userId, List<CartItemResponse> items, BigDecimal totalPrice) {
}
