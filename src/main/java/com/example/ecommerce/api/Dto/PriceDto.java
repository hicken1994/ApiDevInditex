package com.example.ecommerce.api.Dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Dto de la clase Price para trabajar las respuestas de las API.


@Getter
@Setter
@ToString
@EqualsAndHashCode

public class PriceDto implements Serializable {
    private int productId;
    private int brandId;
    private int  priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;


    public PriceDto(int productId, int brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public PriceDto (){

    }
}
