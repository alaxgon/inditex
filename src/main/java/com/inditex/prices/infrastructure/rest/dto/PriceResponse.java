package com.inditex.prices.infrastructure.rest.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO representing the price response returned by the REST API.
 */
@Builder
public record PriceResponse(
        Long productId,
        Long brandId,
        Integer priority,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency
) {
}
