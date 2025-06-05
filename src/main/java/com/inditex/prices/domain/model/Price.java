package com.inditex.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
        Long id,
        Long brandId,
        Long priceList,
        Long productId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priority,
        BigDecimal price,
        String currency) {
}

