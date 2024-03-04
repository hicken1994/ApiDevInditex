package com.example.ecommerce.dominio.jpa;

import com.example.ecommerce.dominio.entidades.PrecioProducto;
import com.example.ecommerce.dominio.repostory.PrecioProductoRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Slf4j
@RequiredArgsConstructor

public class PrecioProductoImpl implements PrecioProductoRepositorio {

    private final JdbcTemplate jdbcTemplateObject;
    private final String tableName = "prices";
    private final String SELECT_PRICES_QUERY = "SELECT Brand_Id, Product_Id, Price_List, Start_Date, End_Date, Priority, Price, Curr, Last_Update, Last_Update_By FROM "
            + tableName + " WHERE Brand_id = ? AND Product_Id = ? AND Start_Date <= ? AND End_Date >= ? ORDER BY priority DESC FETCH FIRST 1 ROWS ONLY";

    /**
     * Obtiene el precio del producto para una marca y producto específicos en una fecha dada.
     * Selecciona todos los precios permitidos. Si hay más de uno, toma el más prioritario.
     * La consulta está optimizada para este propósito.
     */
    public PrecioProducto get(int brandId, int productId, LocalDateTime date) {
        try {
            return this.jdbcTemplateObject.queryForObject(
                    SELECT_PRICES_QUERY,
                    new Object[]{brandId, productId, date, date},
                    (rs, rowNum) -> PrecioProducto.builder()
                            .idProducto(productId)
                            .idMarca(brandId)
                            .tasaTarifaria(rs.getInt("Price_List"))
                            .prioridad(rs.getInt("Priority"))
                            .fechaInicio(rs.getTimestamp("Start_Date").toLocalDateTime())
                            .fechaFin(rs.getTimestamp("End_Date").toLocalDateTime())
                            .precio(rs.getBigDecimal("Price"))
                            .fechaUltimaActualizacion(rs.getTimestamp("Last_Update").toLocalDateTime())
                            .ultimaActualizacionPor(rs.getString("Last_Update_By"))
                            .build()
            );
        } catch (DataAccessException e) {
            log.error("Error al leer los valores de la base de datos para marcaId {}, productoId {} y fecha {}", brandId, productId, date, e);
            return null;
        }
    }
}