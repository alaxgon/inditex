package com.inditex.prices.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JPA entity representing the PRICE table in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "PRICE")
public class PriceEntity {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Brand identifier. */
    private Long brandId;

    /** Price list identifier. */
    private Long priceList;

    /** Product identifier. */
    private Long productId;

    /** Start date and time of the price validity. */
    private LocalDateTime startDate;

    /** End date and time of the price validity. */
    private LocalDateTime endDate;

    /** Priority of the price rule. */
    private Integer priority;

    /** Price amount. */
    private BigDecimal price;

    /** Currency code of the price. */
    private String currency;
}
