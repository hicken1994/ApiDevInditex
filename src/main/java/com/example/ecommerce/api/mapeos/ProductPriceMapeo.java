package com.example.ecommerce.api.mapeos;


import com.example.ecommerce.api.Dto.PriceDto;
import com.example.ecommerce.dominio.entidades.PrecioProducto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
                                 nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)

public interface ProductPriceMapeo {
     @Mappings({@Mapping(source = "idProducto", target = "idProducto"),
            @Mapping(source = "idMarca", target = "idMarca"),
            @Mapping(source = "tasaTarifaria", target = "tasaTarifaria"),
            @Mapping(source = "fechaInicio", target = "fechaInicio"),
            @Mapping(source = "fechaFin", target = "fechaFin"),
            @Mapping(source = "precio", target = "precio")})
    PriceDto priceDto(final PrecioProducto precioProducto);
}

