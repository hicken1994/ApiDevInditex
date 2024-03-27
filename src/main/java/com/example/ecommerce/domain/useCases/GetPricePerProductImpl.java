package com.example.ecommerce.domain.useCases;


import com.example.ecommerce.domain.entities.PriceProduct;
import com.example.ecommerce.exceptions.InditexPriceNotFound;
import com.example.ecommerce.persistence.repository.impl.PriceProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class GetPricePerProductImpl implements GetPricePerProduct {


    private final PriceProductRepositoryImpl precioProductoRepositorioImpl;


    @Override

     public PriceProduct get(final int brandId, final int productId, final LocalDateTime date)
             throws InditexPriceNotFound {

         final PriceProduct productPrice = precioProductoRepositorioImpl.get(brandId, productId, date);
         if (productPrice == null) {
             throw new InditexPriceNotFound(
                     String.format("El precio no se encontro para marca <%d>, producto<%d>, y fecha <%s>", brandId, productId, date));
         }

         return productPrice;
     }





}
