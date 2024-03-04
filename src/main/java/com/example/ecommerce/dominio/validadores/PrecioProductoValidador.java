package com.example.ecommerce.dominio.validadores;

import org.springframework.stereotype.Component;

@Component
public class PrecioProductoValidador {

    public boolean validarParametrosProductoMarca(final int idProducto, final int idMarca) {
        // Verificar que ambos ID sean mayores que 0 para retornarlos como parametros de Entrada.
        return (idProducto > 0) && (idMarca > 0);
    }
}
