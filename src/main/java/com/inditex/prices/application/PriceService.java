package com.inditex.prices.application;

import com.inditex.prices.application.dto.PriceDto;
import com.inditex.prices.application.exception.PriceNotFoundException;
import com.inditex.prices.domain.PriceRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceDto getApplicablePrice(LocalDateTime date,
                                       Long productId,
                                       Long brandId) {
        return priceRepository.findApplicablePrice(brandId, productId, date)
                .map(PriceDto::toDto)
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date.toString()));
    }
}
