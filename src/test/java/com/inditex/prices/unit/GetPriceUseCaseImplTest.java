package com.inditex.prices.unit;

import com.inditex.prices.application.usecase.GetPriceUseCaseImpl;
import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.out.PriceRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPriceUseCaseImplTest {

    private final PriceRepository repo = mock(PriceRepository.class);
    private final GetPriceUseCaseImpl useCase = new GetPriceUseCaseImpl(repo);

    @Test
    void returnsHighestPriority() {
        LocalDateTime ts = LocalDateTime.parse("2020-06-14T10:00:00");

        Price low = new Price(
                1L, 1L, 1L, 35455L, ts.minusHours(1), ts.plusHours(1), 0, BigDecimal.ONE, "EUR"
        );

        Price high = new Price(
                2L, 1L, 1L, 35455L, ts.minusHours(1), ts.plusHours(1), 5, BigDecimal.TEN, "EUR"
        );

        when(repo.findByCriteria(35455L, 1L, ts)).thenReturn(List.of(low, high));

        Price result = useCase.execute(ts, 35455L, 1L);

        assertEquals(high, result);
    }

    @Test
    void throwsWhenEmpty() {
        LocalDateTime now = LocalDateTime.now();

        when(repo.findByCriteria(1L, 1L, now)).thenReturn(List.of());

        assertThrows(PriceNotFoundException.class, () -> useCase.execute(now, 1L, 1L));
    }
}
