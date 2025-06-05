package com.inditex.prices.domain.port.out;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findByCriteria(Long productId, Long brandId, LocalDateTime applicationDate);
}