package com.inditex.prices.infrastructure.db;

import com.inditex.prices.domain.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
class PriceRepositoryTests {

    @Autowired
    private PriceRepository repository;

    @Test
    void shouldReturnHighestPriorityPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Optional<Price> result = repository.findApplicablePrice(1L, 35455L, date);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getPriceList()).isEqualTo(2);
        Assertions.assertThat(result.get().getPrice()).isEqualByComparingTo("25.45");
    }

    @Test
    void shouldReturnEmptyWhenNoPriceMatches() {
        LocalDateTime date = LocalDateTime.of(2030, 1, 1, 0, 0, 0);
        Optional<Price> result = repository.findApplicablePrice(1L, 35455L, date);

        Assertions.assertThat(result).isNotPresent();
    }
}