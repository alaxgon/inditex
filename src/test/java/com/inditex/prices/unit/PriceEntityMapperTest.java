package com.inditex.prices.unit;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.db.entity.PriceEntity;
import com.inditex.prices.infrastructure.db.mapper.PriceEntityMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PriceEntityMapperTest {

    private final PriceEntityMapper m = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    void mapsAllFields() {
        LocalDateTime now = LocalDateTime.now();
        PriceEntity e = new PriceEntity();
        e.setId(1L);
        e.setBrandId(1L);
        e.setProductId(35455L);
        e.setPriceList(2L);
        e.setStartDate(now.minusDays(1));
        e.setEndDate(now.plusDays(1));
        e.setPriority(3);
        e.setPrice(BigDecimal.valueOf(25.45));
        e.setCurrency("EUR");

        Price p = m.toDomain(e);

        assertThat(p)
                .extracting(Price::id, Price::brandId, Price::productId, Price::priceList,
                        Price::priority, Price::price, Price::currency)
                .containsExactly(1L, 1L, 35455L, 2L, 3, BigDecimal.valueOf(25.45), "EUR");
    }

    @Test
    void returnsNullWhenEntityNull() {
        assertThat(m.toDomain(null)).isNull();
    }
}
