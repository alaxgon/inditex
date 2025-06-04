package com.inditex.prices.infrastructure.db.mappers;

import com.inditex.prices.domain.Price;
import com.inditex.prices.infrastructure.db.entities.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class JpaPriceMapper {

    public Price toDomain(PriceEntity entity) {
        if (entity == null) return null;

        return new Price(
                entity.getId(),
                entity.getBrandId(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }

    public PriceEntity toEntity(Price domain) {
        if (domain == null) return null;

        PriceEntity entity = new PriceEntity();
        entity.setId(domain.id());
        entity.setBrandId(domain.brandId());
        entity.setProductId(domain.productId());
        entity.setStartDate(domain.startDate());
        entity.setEndDate(domain.endDate());
        entity.setPriority(domain.priority());
        entity.setPrice(domain.price());
        entity.setCurrency(domain.currency());
        return entity;
    }
}
