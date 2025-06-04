package com.inditex.prices.util;

import com.inditex.prices.domain.Price;
import com.inditex.prices.infrastructure.db.entities.PriceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TestData {

    private TestData() { }

    public static PriceEntity priceEntity() {
        PriceEntity e = new PriceEntity();
        e.setId(1L);
        e.setBrandId(1L);
        e.setProductId(35455L);
        e.setPriceList(1L);
        e.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0));
        e.setEndDate(LocalDateTime.of(2025, 6, 30, 23, 59));
        e.setPriority(1);
        e.setPrice(new BigDecimal("35.50"));
        e.setCurrency("EUR");
        return e;
    }

    public static Price price() {
        PriceEntity entity = priceEntity();
        return new Price(
                entity.getId(),
                entity.getBrandId(),
                entity.getProductId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}