package com.inditex.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Domain model representing a price entry for a product and brand over a specific time period.
 *
 * @param id         the internal identifier
 * @param brandId    the brand identifier
 * @param priceList  the price list identifier
 * @param productId  the product identifier
 * @param startDate  the date and time when the price becomes effective
 * @param endDate    the date and time when the price expires
 * @param priority   the priority of the price (used to break ties)
 * @param price      the price amount
 * @param currency   the currency in which the price is expressed
 */
public record Price(
        Long id,
        Long brandId,
        Long priceList,
        Long productId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priority,
        BigDecimal price,
        String currency
) {
}
