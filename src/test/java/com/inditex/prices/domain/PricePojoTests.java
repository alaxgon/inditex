package com.inditex.prices.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PricePojoTests {

    @Test
    void builderAndGettersWork() {
        Price price = Price.builder()
                .id(1L)
                .brandId(1L)
                .productId(35455L)
                .priceList(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .price(BigDecimal.TEN)
                .currency("EUR")
                .priority(0)
                .build();

        assertThat(price.getId()).isEqualTo(1L);
        assertThat(price.getBrandId()).isEqualTo(1L);
        assertThat(price.getCurrency()).isEqualTo("EUR");
    }
}