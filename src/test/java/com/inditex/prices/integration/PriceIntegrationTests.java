package com.inditex.prices.integration;

import com.inditex.prices.application.dto.PriceDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    // Official 5 scenarios
    @ParameterizedTest(name = "{index} ⇒ date={0} → priceList {3} / price {4}")
    @CsvSource({
            // date | productId | brandId | expectedPriceList | expectedPrice
            "2020-06-14T10:00:00,35455,1,1,35.50",
            "2020-06-14T16:00:00,35455,1,2,25.45",
            "2020-06-14T21:00:00,35455,1,1,35.50",
            "2020-06-15T10:00:00,35455,1,3,30.50",
            "2020-06-16T21:00:00,35455,1,4,38.95"
    })
    void shouldReturnCorrectPrice(String date, Long productId, Long brandId,
                                  Long expectedPriceList, BigDecimal expectedPrice) {
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(
                "/prices?date=" + date + "&productId=" + productId + "&brandId=" + brandId,
                PriceDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PriceDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getPriceList()).isEqualTo(expectedPriceList);
        assertThat(body.getPrice()).isEqualTo(expectedPrice);
    }

    // Error scenario: no price found -> 404
    @ParameterizedTest
    @CsvSource({
            "2020-01-01T00:00:00,35455,1",  // date out of range
            "2020-06-14T10:00:00,99999,1"   // unknown product
    })
    void shouldReturn404WhenNoPrice(String date, Long productId, Long brandId) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/prices?date=" + date + "&productId=" + productId + "&brandId=" + brandId,
                String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}