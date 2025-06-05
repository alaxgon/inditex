package com.inditex.prices.infrastructure.rest;

import com.inditex.prices.application.usecase.GetPriceUseCase;
import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceUseCase useCase;

    @Test
    void returnsPriceSuccessfully() throws Exception {
        LocalDateTime ts = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price mockPrice = new Price(1L, 1L, 1L, 35455L,
                ts.minusDays(1), ts.plusDays(1),
                1, BigDecimal.valueOf(35.5), "EUR");

        when(useCase.execute(ts, 35455L, 1L)).thenReturn(mockPrice);

        mockMvc.perform(get("/prices")
                        .param("date", ts.toString())
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void returns404WhenPriceNotFound() throws Exception {
        LocalDateTime ts = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(useCase.execute(ts, 35455L, 1L))
                .thenThrow(new PriceNotFoundException(35455L, 1L, ts));

        mockMvc.perform(get("/prices")
                        .param("date", ts.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No price found for product 35455 and brand 1 on 2020-06-14T10:00"));
    }
}
