package com.inditex.prices.domain.exception;

import java.time.LocalDateTime;

/**
 * Exception thrown when no applicable price is found for the given criteria.
 */
public class PriceNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a detailed message including the search criteria.
     *
     * @param productId        the product ID
     * @param brandId          the brand ID
     * @param applicationDate  the date for which the price was searched
     */
    public PriceNotFoundException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super("No price found for product %d and brand %d on %s"
                .formatted(productId, brandId, applicationDate));
    }
}
