package com.inditex.prices.infrastructure.db.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.out.PriceRepository;
import com.inditex.prices.infrastructure.db.mapper.PriceEntityMapper;
import com.inditex.prices.infrastructure.db.repository.PriceJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository jpaRepository;
    private final PriceEntityMapper mapper;

    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository, PriceEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Price> findByCriteria(Long productId, Long brandId, LocalDateTime applicationDate) {
       return  jpaRepository.searchPrices(productId, brandId, applicationDate)
                .stream()
                .map(mapper::toDomain).toList();
    }
}