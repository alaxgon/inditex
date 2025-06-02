package com.inditex.prices.application;

import com.inditex.prices.application.dto.PriceDto;
import com.inditex.prices.application.exception.PriceNotFoundException;
import com.inditex.prices.domain.Price;
import com.inditex.prices.infrastructure.db.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceDto getApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findApplicablePrice(brandId, productId, date)
                .map(this::mapToDto)
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date.toString()));
    }

    private PriceDto mapToDto(Price price) {
        return PriceDto.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .currency(price.getCurrency())
                .build();
    }
}