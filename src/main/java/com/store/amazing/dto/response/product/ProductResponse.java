package com.store.amazing.dto.response.product;

import java.math.BigDecimal;

public record ProductResponse(long id,
                              String name,
                              String description,
                              BigDecimal price,
                              Integer stock,
                              String category) {
}
