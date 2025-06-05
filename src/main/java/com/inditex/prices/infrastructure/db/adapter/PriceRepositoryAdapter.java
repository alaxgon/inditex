package com.inditex.prices.infrastructure.db.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.out.PriceRepository;
import com.inditex.prices.infrastructure.db.mapper.PriceEntityMapper;
import com.inditex.prices.infrastructure.db.repository.PriceJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Adapter that bridges the domain repository interface with the JPA implementation.
 */
@Component
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository jpaRepository;
    private final PriceEntityMapper mapper;

    /**
     * Constructs the adapter with the required dependencies.
     *
     * @param jpaRepository the underlying JPA repository
     * @param mapper        the entity-to-domain mapper
     */
    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository, PriceEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /**
     * Finds matching prices using the provided criteria.
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date for which to find the price
     * @return a list of matching {@link Price} domain objects
     */
    @Override
    public List<Price> findByCriteria(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaRepository
                .searchPrices(productId, brandId, applicationDate)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
