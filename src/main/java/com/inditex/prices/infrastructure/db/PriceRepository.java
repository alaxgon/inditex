package com.inditex.prices.infrastructure.db;

import com.inditex.prices.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Returns the price with the highest priority that is valid for the given parameters.
     * Native query is used for efficiency.
     */
    @Query(value = """
            SELECT *
            FROM price
            WHERE brand_id = :brandId
              AND product_id = :productId
              AND :applicationDate BETWEEN start_date AND end_date
            ORDER BY priority DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<Price> findApplicablePrice(@Param("brandId") Long brandId,
                                        @Param("productId") Long productId,
                                        @Param("applicationDate") LocalDateTime applicationDate);
}