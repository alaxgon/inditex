package com.inditex.prices.infrastructure.db.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE")
@Data
public class PriceEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BRAND_ID")   private Long brandId;
    @Column(name = "PRODUCT_ID") private Long productId;
    @Column(name = "PRICE_LIST") private Long priceList;
    @Column(name = "START_DATE") private LocalDateTime startDate;
    @Column(name = "END_DATE")   private LocalDateTime endDate;
    @Column(name = "PRIORITY")   private Integer priority;
    @Column(name = "PRICE")      private BigDecimal price;
    @Column(name = "CURRENCY")       private String currency;

}
