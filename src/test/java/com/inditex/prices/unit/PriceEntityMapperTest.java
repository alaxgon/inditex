package com.inditex.prices.unit;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.db.entity.PriceEntity;
import com.inditex.prices.infrastructure.db.mapper.PriceEntityMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceEntityMapperTest {

    private final PriceEntityMapper mapper = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    void mapsAllFields() {
        LocalDateTime now = LocalDateTime.now();

        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setBrandId(1L);
        entity.setProductId(35455L);
        entity.setPriceList(2L);
        entity.setStartDate(now.minusDays(1));
        entity.setEndDate(now.plusDays(1));
        entity.setPriority(3);
        entity.setPrice(BigDecimal.valueOf(25.45));
        entity.setCurrency("EUR");

        Price price = mapper.toDomain(entity);

        assertThat(price).extracting(
                Price::id,
                Price::brandId,
                Price::productId,
                Price::priceList,
                Price::priority,
                Price::price,
                Price::currency
        ).containsExactly(1L, 1L, 35455L, 2L, 3, BigDecimal.valueOf(25.45), "EUR");
    }

    @Test
    void returnsNullWhenEntityNull() {
        assertThat(mapper.toDomain(null)).isNull();
    }
}
