package com.inditex.prices.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00:00,35455,1,35.5,1",
            "2020-06-14T16:00:00,35455,1,25.45,2",
            "2020-06-14T21:00:00,35455,1,35.5,1",
            "2020-06-15T10:00:00,35455,1,30.5,3",
            "2020-06-16T21:00:00,35455,1,38.95,4"
    })
    void shouldReturnCorrectPrice(
            String date,
            int productId,
            int brandId,
            BigDecimal expectedPrice,
            int expectedPriceList
    ) throws Exception {
        mockMvc.perform(
                        get("/prices")
                                .param("date", date)
                                .param("productId", String.valueOf(productId))
                                .param("brandId", String.valueOf(brandId))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.priceList").value(expectedPriceList));
    }

    @ParameterizedTest
    @CsvSource({
            "2020-06-14T00:00:00,35455,1,35.5,1",
            "2020-06-14T18:30:00,35455,1,25.45,2",
            "2020-06-15T11:00:00,35455,1,30.5,3"
    })
    void shouldReturnCorrectPriceOnBoundaries(
            String date,
            int productId,
            int brandId,
            BigDecimal expectedPrice,
            int expectedPriceList
    ) throws Exception {
        mockMvc.perform(
                        get("/prices")
                                .param("date", date)
                                .param("productId", String.valueOf(productId))
                                .param("brandId", String.valueOf(brandId))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.priceList").value(expectedPriceList));
    }

    @ParameterizedTest
    @CsvSource({
            "2020-01-01T00:00:00,35455,1",
            "2020-06-14T10:00:00,99999,1",
            "2020-06-14T10:00:00,35455,2"
    })
    void shouldReturn404WhenNoPrice(String date, int productId, int brandId) throws Exception {
        mockMvc.perform(
                        get("/prices")
                                .param("date", date)
                                .param("productId", String.valueOf(productId))
                                .param("brandId", String.valueOf(brandId))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenBadRequest() throws Exception {
        mockMvc.perform(
                        get("/prices")
                                .param("date", "invalid-date")
                                .param("productId", "ABC")
                                .param("brandId", "")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
}
