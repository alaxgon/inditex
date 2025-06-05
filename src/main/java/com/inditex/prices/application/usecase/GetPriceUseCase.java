package com.inditex.prices.application.usecase;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    Price execute(LocalDateTime applicationDate, Long productId, Long brandId);
}