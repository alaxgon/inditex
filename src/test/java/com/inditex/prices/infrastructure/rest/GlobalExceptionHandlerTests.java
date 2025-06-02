package com.inditex.prices.infrastructure.rest;

import com.inditex.prices.application.PriceService;
import com.inditex.prices.application.exception.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PriceController.class)
class GlobalExceptionHandlerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    void shouldReturn404WhenPriceNotFound() throws Exception {
        Mockito.when(priceService.getApplicablePrice(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L))
                .thenThrow(new PriceNotFoundException(35455L, 1L, "2020-06-14T10:00:00"));

        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void shouldReturn400WhenBadParam() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "bad-date")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    void shouldReturn500WhenGenericError() throws Exception {
        Mockito.when(priceService.getApplicablePrice(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L))
                .thenThrow(new RuntimeException("Unexpected boom"));

        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"));
    }

}