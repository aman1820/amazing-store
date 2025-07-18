package com.store.amazing.dto.response.cart;

import java.math.BigDecimal;

public record CartItemResponse(String name, BigDecimal price, Integer quantity, BigDecimal totalPrice) {
}
