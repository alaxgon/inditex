package com.inditex.prices.infrastructure.db.mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.inditex.prices.domain.Price;
import com.inditex.prices.infrastructure.db.entities.PriceEntity;
import com.inditex.prices.util.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class JpaPriceMapperTest {

    private JpaPriceMapper mapper;
    private PriceEntity entity;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(JpaPriceMapper.class);
        entity = TestData.priceEntity();
    }

    @Test
    @DisplayName("toDomain maps all fields correctly")
    void toDomain_mapsAllFields() {
        Price domain = mapper.toDomain(entity);

        assertAll(
                () -> assertEquals(entity.getId(),         domain.id()),
                () -> assertEquals(entity.getBrandId(),    domain.brandId()),
                () -> assertEquals(entity.getProductId(),  domain.productId()),
                () -> assertEquals(entity.getStartDate(),  domain.startDate()),
                () -> assertEquals(entity.getEndDate(),    domain.endDate()),
                () -> assertEquals(entity.getPriority(),   domain.priority()),
                () -> assertEquals(entity.getPrice(),      domain.price()),
                () -> assertEquals(entity.getCurrency(),   domain.currency())
        );
    }
}
