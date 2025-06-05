package com.inditex.prices.application.usecase;

import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.out.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    public GetPriceUseCaseImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price execute(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findByCriteria(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparing(Price::priority))
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));
    }
}