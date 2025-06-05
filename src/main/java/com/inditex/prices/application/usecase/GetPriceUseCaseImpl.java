package com.inditex.prices.application.usecase;

import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.out.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Default implementation of {@link GetPriceUseCase}.
 */
@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    /**
     * Constructs a new instance with the given repository.
     *
     * @param priceRepository the repository used to query prices
     */
    public GetPriceUseCaseImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Retrieves the applicable price using the highest priority among the matches.
     *
     * @param applicationDate the application date
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @return the applicable {@link Price}
     * @throws PriceNotFoundException if no price is found
     */
    @Override
    public Price execute(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findByCriteria(productId, brandId, applicationDate).stream()
                .max(Comparator.comparing(Price::priority))
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));
    }
}
