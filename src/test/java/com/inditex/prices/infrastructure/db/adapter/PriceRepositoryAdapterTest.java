package com.inditex.prices.infrastructure.db.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.db.entity.PriceEntity;
import com.inditex.prices.infrastructure.db.mapper.PriceEntityMapper;
import com.inditex.prices.infrastructure.db.repository.PriceJpaRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceRepositoryAdapterTest {

    private final PriceJpaRepository jpaRepository = mock(PriceJpaRepository.class);
    private final PriceEntityMapper mapper = mock(PriceEntityMapper.class);
    private final PriceRepositoryAdapter adapter = new PriceRepositoryAdapter(jpaRepository, mapper);

    @Test
    void shouldReturnMappedPricesFromJpaRepository() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();

        PriceEntity entity = new PriceEntity();
        Price expectedPrice = new Price(
                1L,
                brandId,
                1L,
                productId,
                date.minusDays(1),
                date.plusDays(1),
                0,
                BigDecimal.TEN,
                "EUR"
        );

        when(jpaRepository.searchPrices(productId, brandId, date)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(expectedPrice);

        List<Price> result = adapter.findByCriteria(productId, brandId, date);

        assertThat(result).containsExactly(expectedPrice);
        verify(jpaRepository).searchPrices(productId, brandId, date);
        verify(mapper).toDomain(entity);
    }
}
