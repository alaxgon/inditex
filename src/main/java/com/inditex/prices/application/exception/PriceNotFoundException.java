package com.inditex.prices.application.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Long productId, Long brandId, String date) {
        super("Price not found for product=" + productId + ", brand=" + brandId + ", date=" + date);
    }
}