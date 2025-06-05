package com.inditex.prices.infrastructure.db.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.db.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PriceEntityMapperTest {

    @Autowired
    private PriceEntityMapper mapper;

    @Test
    void mapsEntityToDomain() {
        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setBrandId(1L);
        entity.setPriceList(2L);
        entity.setProductId(35455L);
        entity.setStartDate(LocalDateTime.now().minusDays(1));
        entity.setEndDate(LocalDateTime.now().plusDays(1));
        entity.setPriority(1);
        entity.setPrice(BigDecimal.valueOf(35.50));
        entity.setCurrency("EUR");

        Price domain = mapper.toDomain(entity);

        assertThat(domain).isNotNull();
        assertThat(domain.id()).isEqualTo(1L);
        assertThat(domain.brandId()).isEqualTo(1L);
        assertThat(domain.priceList()).isEqualTo(2L);
        assertThat(domain.productId()).isEqualTo(35455L);
        assertThat(domain.currency()).isEqualTo("EUR");
    }

    @Test
    void mapsDomainToEntity() {
        Price domain = new Price(
                1L,
                1L,
                2L,
                35455L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                1,
                BigDecimal.valueOf(35.50),
                "EUR"
        );

        PriceEntity entity = mapper.toEntity(domain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getBrandId()).isEqualTo(1L);
        assertThat(entity.getPriceList()).isEqualTo(2L);
        assertThat(entity.getProductId()).isEqualTo(35455L);
        assertThat(entity.getCurrency()).isEqualTo("EUR");
    }
}
