package com.example.ecommerce.boot;


import com.example.ecommerce.dominio.entidades.PrecioProducto;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.example.ecommerce"})
public class AdaptadorInditex {

    private static final Function<String, PrecioProducto> csv2PrecioObj = (line) -> {
        String[] p = line.split(",");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

        return PrecioProducto.builder()
                .idProducto(Integer.parseInt(p[4]))
                .idMarca(Integer.parseInt(p[0]))
                .tasaTarifaria(Integer.parseInt(p[3]))
                .prioridad(Integer.parseInt(p[5]))
                .fechaInicio(LocalDateTime.parse(p[1], formatoFecha))
                .fechaFin(LocalDateTime.parse(p[2], formatoFecha))
                .precio(BigDecimal.valueOf(Double.parseDouble(p[6])))
                .fechaUltimaActualizacion(LocalDateTime.parse(p[8], formatoFecha))
                .ultimaActualizacionPor(p[9])
                .build();
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(AdaptadorInditex.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread(AdaptadorInditex::shutdown));

        log.info("===================== AdaptadorInditex Iniciando ========================");
    }

    private static void shutdown() {

        log.info("===================== AdaptadorInditex Detenido ========================");
    }

    private List<PrecioProducto> processInputFile(String inputFilePath) {

        List<PrecioProducto> inputList;

        try {
            File inputF = new File("src/main/resources/prices.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            inputList = br.lines().skip(1).map(csv2PrecioObj).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            log.info("error reading file: <{}>", e.getMessage());
            return null;
        }

        return inputList;
    }

    @PostConstruct
    private void initDb() throws SQLException {
        log.info("****** Creando la tabla <{}> e Insertando datos de prueba ******", "Precios");

        // Desactivar la auto-confirmación
        Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().setAutoCommit(false);

        String[] sqlStatements = {
                "DROP TABLE prices IF EXISTS",
                "CREATE TABLE prices(Brand_Id int,Start_Date TIMESTAMP,End_Date TIMESTAMP,Price_List int,Product_Id int," +
                        "Priority int,Price DECIMAL(20, 2),Curr varchar(3),Last_Update TIMESTAMP,Last_Update_By varchar(64))"
        };

        Arrays.stream(sqlStatements).forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        // Preparar la sentencia de inserción
        String INSERT_QUERY = "INSERT INTO prices VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(INSERT_QUERY);

        // Leer el archivo csv
        List<PrecioProducto> precioList = processInputFile("src/main/resources/prices.csv");

        // Insertar por lotes
        assert precioList != null;
        for (PrecioProducto row : precioList) {
            ps.setInt(1, row.getIdMarca());
            Timestamp fechaInicio = Timestamp.valueOf(row.getFechaInicio());
            ps.setTimestamp(2, fechaInicio);
            Timestamp fechaFin = Timestamp.valueOf(row.getFechaFin());
            ps.setTimestamp(3, fechaFin);
            ps.setInt(4,row.getTasaTarifaria());
            ps.setInt(5, row.getIdProducto());
            ps.setInt(6,row.getPrioridad());
            ps.setBigDecimal(7, row.getPrecio());
            ps.setString(8,"EUR");
            Timestamp fechaUltimaAct = Timestamp.valueOf(row.getFechaUltimaActualizacion());
            ps.setTimestamp(9, fechaUltimaAct);
            ps.setString(10,row.getUltimaActualizacionPor());
            ps.addBatch();
        }

        ps.executeBatch();
        ps.close();

        // Confirmar la transacción
        jdbcTemplate.getDataSource().getConnection().commit();

        log.info("****** Datos insertados correctamente ******");
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }

}