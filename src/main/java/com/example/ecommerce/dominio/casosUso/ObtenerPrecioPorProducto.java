package com.example.ecommerce.dominio.casosUso;

import com.example.ecommerce.dominio.entidades.PrecioProducto;
import com.example.ecommerce.dominio.excepciones.InditexParametersNotValid;
import com.example.ecommerce.dominio.excepciones.InditexPriceNotFound;

import java.time.LocalDateTime;

public interface ObtenerPrecioPorProducto {

    PrecioProducto obtenerPrecioPorProductoFecha(int marcaId, int productoId, LocalDateTime date)
        throws InditexPriceNotFound, InditexParametersNotValid;
}
