package com.inditex.prices.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(Long brandId, Long productId, LocalDateTime dateTime);
}