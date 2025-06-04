package com.inditex.prices.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.inditex.prices.application.dto.PriceDto;
import com.inditex.prices.application.exception.PriceNotFoundException;
import com.inditex.prices.domain.PriceRepository;
import com.inditex.prices.util.TestData;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link PriceService} using Mockito (no Spring context).
 */
@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    private com.inditex.prices.domain.Price domainPrice;
    private LocalDateTime queryDate;
    private Long productId;
    private Long brandId;

    @BeforeEach
    void setUp() {
        queryDate  = LocalDateTime.of(2025, 6, 4, 10, 0);
        productId  = 35455L;
        brandId    = 1L;
        domainPrice = TestData.price();
    }

    @Nested
    @DisplayName("When a matching price exists")
    class WhenMatchingPriceExists {
        @Test
        @DisplayName("It returns the mapped PriceDto")
        void shouldReturnPriceDto() {
            when(priceRepository.findApplicablePrice(brandId, productId, queryDate))
                    .thenReturn(Optional.of(domainPrice));

            PriceDto result = priceService.getApplicablePrice(queryDate, productId, brandId);

            assertAll(
                    () -> assertEquals(domainPrice.productId(),   result.getProductId()),
                    () -> assertEquals(domainPrice.brandId(),     result.getBrandId()),
                    () -> assertEquals(domainPrice.priceList(),   result.getPriceList()),
                    () -> assertEquals(domainPrice.price(),       result.getPrice()),
                    () -> assertEquals(domainPrice.currency(),    result.getCurrency()),
                    () -> assertEquals(domainPrice.startDate(),   result.getStartDate()),
                    () -> assertEquals(domainPrice.endDate(),     result.getEndDate())
            );

            verify(priceRepository).findApplicablePrice(brandId, productId, queryDate);
        }
    }

    @Nested
    @DisplayName("When no matching price exists")
    class WhenNoMatchingPrice {
        @Test
        @DisplayName("It throws PriceNotFoundException")
        void shouldThrowException() {
            when(priceRepository.findApplicablePrice(brandId, productId, queryDate))
                    .thenReturn(Optional.empty());

            assertThrows(PriceNotFoundException.class,
                    () -> priceService.getApplicablePrice(queryDate, productId, brandId));
        }
    }
}
