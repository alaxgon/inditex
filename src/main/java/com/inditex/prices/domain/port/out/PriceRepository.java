package com.inditex.prices.domain.port.out;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Port for accessing price data from a persistence layer.
 */
public interface PriceRepository {

    /**
     * Retrieves all price entries matching the given criteria.
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date of price application
     * @return a list of matching {@link Price} entries
     */
    List<Price> findByCriteria(Long productId, Long brandId, LocalDateTime applicationDate);
}
