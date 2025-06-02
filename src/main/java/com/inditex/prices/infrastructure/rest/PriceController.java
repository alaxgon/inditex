package com.inditex.prices.infrastructure.rest;

import com.inditex.prices.application.PriceService;
import com.inditex.prices.application.dto.PriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceDto> getApplicablePrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        PriceDto dto = priceService.getApplicablePrice(date, productId, brandId);
        return ResponseEntity.ok(dto);
    }
}