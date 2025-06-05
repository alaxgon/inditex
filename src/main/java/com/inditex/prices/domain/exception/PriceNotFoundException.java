package com.inditex.prices.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super("No price found for product %d and brand %d on %s".formatted(productId, brandId, applicationDate));
    }
}