package com.example.ecommerce.dominio.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class PrecioProducto {

   private int idProducto;

   private int idMarca;

   private int tasaTarifaria;

   private int prioridad;

   private LocalDateTime fechaInicio;

   private LocalDateTime fechaFin;

   private BigDecimal precio;

   private LocalDateTime fechaUltimaActualizacion;

   private String ultimaActualizacionPor;

}