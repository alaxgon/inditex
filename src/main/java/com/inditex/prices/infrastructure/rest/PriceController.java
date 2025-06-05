package com.inditex.prices.infrastructure.rest;

import com.inditex.prices.application.usecase.GetPriceUseCase;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.rest.dto.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * REST controller that handles price lookup requests.
 */
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetPriceUseCase useCase;

    public PriceController(GetPriceUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Returns the applicable price for the given parameters.
     *
     * @param date      application date
     * @param productId product identifier
     * @param brandId   brand identifier
     * @return the price response DTO
     */
    @GetMapping
    public PriceResponse findPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        Price price = useCase.execute(date, productId, brandId);
        return PriceResponse.builder()
                .productId(price.productId())
                .brandId(price.brandId())
                .priority(price.priority())
                .priceList(price.priceList())
                .startDate(price.startDate())
                .endDate(price.endDate())
                .price(price.price())
                .currency(price.currency())
                .build();
    }
}
