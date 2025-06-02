package com.inditex.prices.application;

import com.inditex.prices.application.dto.PriceDto;
import com.inditex.prices.application.exception.PriceNotFoundException;
import com.inditex.prices.domain.Price;
import com.inditex.prices.infrastructure.db.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTests {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    private Price samplePrice;

    @BeforeEach
    void setUp() {
        samplePrice = Price.builder()
                .id(1L)
                .brandId(1L)
                .productId(35455L)
                .priceList(2)
                .priority(1)
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();
    }

    @Test
    void shouldReturnPriceDto_whenPriceExists() {
        when(priceRepository.findApplicablePrice(anyLong(), anyLong(), any()))
                .thenReturn(Optional.of(samplePrice));

        PriceDto dto = priceService.getApplicablePrice(LocalDateTime.now(), 35455L, 1L);

        assertThat(dto.getPrice()).isEqualByComparingTo("35.50");
        assertThat(dto.getPriceList()).isEqualTo(2);
        assertThat(dto.getCurrency()).isEqualTo("EUR");
    }

    @Test
    void shouldThrowException_whenPriceNotFound() {
        when(priceRepository.findApplicablePrice(anyLong(), anyLong(), any()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> priceService.getApplicablePrice(LocalDateTime.now(), 35455L, 1L))
                .isInstanceOf(PriceNotFoundException.class)
                .hasMessageContaining("Price not found");
    }
}