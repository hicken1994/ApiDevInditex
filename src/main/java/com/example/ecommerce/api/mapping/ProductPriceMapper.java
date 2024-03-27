package com.example.ecommerce.api.mapping;


import com.example.ecommerce.api.Dto.PriceDto;
import com.example.ecommerce.domain.entities.PriceProduct;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
                                 nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)

public interface ProductPriceMapper {
     @Mappings({@Mapping(source = "productId", target = "productId"),
            @Mapping(source = "brandId", target = "brandId"),
            @Mapping(source = "priceList", target = "priceList"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),
            @Mapping(source = "price", target = "price")})
    PriceDto priceDto(final PriceProduct precioProducto);
}

