package com.example.ecommerce.api.controller;

import com.example.ecommerce.api.Dto.PriceDto;
import com.example.ecommerce.api.mapeos.ProductPriceMapeo;
import com.example.ecommerce.dominio.casosUso.ObtenerPrecioPorProducto;
import com.example.ecommerce.dominio.entidades.PrecioProducto;
import com.example.ecommerce.dominio.excepciones.InditexParametersNotValid;
import com.example.ecommerce.dominio.excepciones.InditexPriceNotFound;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/inditex/api")
@RequiredArgsConstructor
@Slf4j
public class ControladorPrecio {

    private static final String MESSAGE_INTERNAL_ERROR = "Internal server error";

    private static final String MESSAGE_INVALID_PARAMETERS = "Parametros Invalidos";

    private static final String MESSAGE_PRICE_NOT_FOUND = "Precio No Encontrado";

    private static final String MESSAGE_PRICE_FOUND = "Precio Encontrado";

    private final ProductPriceMapeo productPriceMapeo;

    private final ObtenerPrecioPorProducto obtenerPrecioPorProducto;

    @ApiOperation(value = "Obtener el precio del producto a partir de una marca, un producto y una fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MESSAGE_PRICE_FOUND,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceDto.class)) }),
            @ApiResponse(responseCode = "422", description = MESSAGE_INVALID_PARAMETERS,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = MESSAGE_PRICE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = "500", description = MESSAGE_INTERNAL_ERROR,
                    content = @Content)}
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/price")
    public ResponseEntity<PriceDto> obtenerPrecioProducto(
            @ApiParam(required = true) @RequestParam("priceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime priceDate,
            @ApiParam(required = true) @Min(1) int productId,
            @ApiParam(required = true) @Min(1) int brandId)
            throws InditexPriceNotFound, InditexParametersNotValid {

        log.info("Obtener para producto tarifa recibida, idProducto <{}>, idMarca<{}>, fecha <{}>", productId, brandId, priceDate);

        final PrecioProducto productPrice = obtenerPrecioPorProducto.obtenerPrecioPorProductoFecha(brandId, productId, priceDate);

        final PriceDto productPriceDto = productPriceMapeo.priceDto(productPrice);

        return new ResponseEntity<>(productPriceDto, HttpStatus.OK);
    }
}