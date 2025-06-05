package com.inditex.prices.application.usecase;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;

/**
 * Use case to retrieve the applicable price.
 */
public interface GetPriceUseCase {

    /**
     * Finds the applicable price for the given parameters.
     *
     * @param applicationDate the date of the price application
     * @param productId the product identifier
     * @param brandId the brand identifier
     * @return the applicable {@link Price}
     */
    Price execute(LocalDateTime applicationDate, Long productId, Long brandId);
}
