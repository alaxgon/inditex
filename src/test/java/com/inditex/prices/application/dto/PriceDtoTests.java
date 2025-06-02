package com.inditex.prices.application.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceDtoTests {

    @Test
    void builderAndGettersWork() {
        PriceDto dto = PriceDto.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1)
                .startDate(LocalDateTime.MIN)
                .endDate(LocalDateTime.MAX)
                .price(new BigDecimal("25.45"))
                .currency("EUR")
                .build();

        assertThat(dto.getPrice()).isEqualByComparingTo("25.45");
        assertThat(dto.getCurrency()).isEqualTo("EUR");
    }
}