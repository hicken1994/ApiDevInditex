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
    private int idProducto;
    private int idMarca;
    private int  tasaTarifaria;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal precio;


    public PriceDto(int idProducto, int idMarca, int tasaTarifaria, LocalDateTime fechaInicio, LocalDateTime fechaFin, BigDecimal precio) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.tasaTarifaria = tasaTarifaria;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
    }

    public PriceDto (){

    }
}
