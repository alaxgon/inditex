package com.inditex.prices.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJpaTest
class PriceEntityTests {

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void shouldSeedFourPriceRows() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM price", Integer.class);
        Assertions.assertThat(count).isEqualTo(4);
    }
}