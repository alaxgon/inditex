package com.inditex.prices.infrastructure.db.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.db.entity.PriceEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link PriceEntity} and {@link Price}.
 */
@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    /**
     * Converts a {@link PriceEntity} to a domain {@link Price}.
     *
     * @param entity the entity to convert
     * @return the domain object
     */
    Price toDomain(PriceEntity entity);

    /**
     * Converts a domain {@link Price} to a {@link PriceEntity}.
     *
     * @param domain the domain object to convert
     * @return the entity object
     */
    PriceEntity toEntity(Price domain);
}
