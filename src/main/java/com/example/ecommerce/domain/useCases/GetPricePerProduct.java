package com.example.ecommerce.domain.useCases;

import com.example.ecommerce.domain.entities.PriceProduct;
import com.example.ecommerce.exceptions.InditexParametersNotValid;
import com.example.ecommerce.exceptions.InditexPriceNotFound;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface GetPricePerProduct {

    PriceProduct get(int marcaId, int productoId, LocalDateTime date)
        throws InditexPriceNotFound, InditexParametersNotValid;
}
