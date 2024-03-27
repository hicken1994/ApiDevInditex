package com.example.ecommerce.persistence.repository;

import com.example.ecommerce.domain.entities.PriceProduct;

import java.time.LocalDateTime;

public interface CustomPriceProductRepository {
    PriceProduct get(int brandId, int productId, LocalDateTime date);
}
