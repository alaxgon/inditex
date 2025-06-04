package com.inditex.prices.infrastructure.db;

import com.inditex.prices.domain.Price;
import com.inditex.prices.domain.PriceRepository;
import com.inditex.prices.infrastructure.db.mappers.JpaPriceMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaPriceRepository implements PriceRepository {

    private final SpringDataPriceJpa data;
    private final JpaPriceMapper mapper;

    @Override
    public Optional<Price> findApplicablePrice(Long brandId,
                                               Long productId,
                                               LocalDateTime dateTime) {

        return data.findApplicablePrice(brandId, productId, dateTime)
                .map(mapper::toDomain);
    }
}
