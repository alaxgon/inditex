package com.inditex.prices.infrastructure.db.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.db.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {
    PriceEntityMapper INSTANCE = Mappers.getMapper(PriceEntityMapper.class);

    Price toDomain(PriceEntity entity);
    PriceEntity toEntity(Price domain);
}
