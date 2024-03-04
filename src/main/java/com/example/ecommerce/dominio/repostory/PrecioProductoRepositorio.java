package com.example.ecommerce.dominio.repostory;

import com.example.ecommerce.dominio.entidades.PrecioProducto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PrecioProductoRepositorio {
    PrecioProducto get(int idMarca, int idProducto, LocalDateTime fecha);
}


