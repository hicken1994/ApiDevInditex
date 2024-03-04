package com.example.ecommerce.dominio.casosUso;


import com.example.ecommerce.dominio.entidades.PrecioProducto;
import com.example.ecommerce.dominio.excepciones.InditexParametersNotValid;
import com.example.ecommerce.dominio.excepciones.InditexPriceNotFound;
import com.example.ecommerce.dominio.repostory.PrecioProductoRepositorio;
import com.example.ecommerce.dominio.repostory.stereotypes.UseCase;
import com.example.ecommerce.dominio.validadores.PrecioProductoValidador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class ObtenerPrecioPorProductoImpl implements ObtenerPrecioPorProducto {
     private final PrecioProductoRepositorio precioProductoRepositorio;
     private final PrecioProductoValidador precioProductoValidador;

     @Override
     public PrecioProducto obtenerPrecioPorProductoFecha(final int idMarca, final int idProducto, final LocalDateTime fecha)
             throws InditexPriceNotFound, InditexParametersNotValid {

         if (!precioProductoValidador.validarParametrosProductoMarca(idMarca, idProducto)) {
             throw new InditexParametersNotValid(
                     String.format("Parametros invalidos para marca <%d>, producto <%d>, y fecha <%s>", idMarca, idProducto, fecha));
         }

         final PrecioProducto productPrice = precioProductoRepositorio.get(idMarca, idProducto, fecha);
         if (productPrice == null) {
             throw new InditexPriceNotFound(
                     String.format("El precio no se encontro para marca <%d>, producto<%d>, y fecha <%s>", idMarca, idProducto, fecha));
         }

         return productPrice;
     }



}
