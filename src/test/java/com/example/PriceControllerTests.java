package com.example;

import com.example.ecommerce.api.Dto.PriceDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = com.example.ecommerce.boot.AdaptadorInditex.class)
class PriceControllerTests {

    private static RestTemplate templateRest = null;

    private final String URL_TEST1 = "price?brandId=1&productId=35455&priceDate=2020-06-14T10:00:00Z";

    private final String URL_TEST2 = "price?brandId=1&productId=35455&priceDate=2020-06-14T16:00:00Z";

    private final String URL_TEST3 = "price?brandId=1&productId=35455&priceDate=2020-06-14T21:00:00Z";

    private final String URL_TEST4 = "price?brandId=1&productId=35455&priceDate=2020-06-15T10:00:00Z";

    private final String URL_TEST5 = "price?brandId=1&productId=35455&priceDate=2020-06-16T21:00:00Z";

    private final String TEST6_URL_NO_ENCONTRADA = "price?brandId=2&productId=35455&priceDate=2020-06-16T21:00:00Z";

    private final String TEST6_PARAMETROS_NO_VALIDOS_URL = "price?brandId=-2&productId=35455&priceDate=2020-06-16T21:00:00Z";

    private final int ID_PRODUCTO = 35455;

    private final int ID_MARCA = 1;

    private final int TASA_TARIFARIA_1 = 1;

    private final int TASA_TARIFARIA_2 = 2;

    private final int TASA_TARIFARIA_3 = 3;

    private final int TASA_TARIFARIA_4 = 4;

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @LocalServerPort
    private int puerto;

    private String baseUrl = "http://localhost";
    @BeforeAll
    public static void init() {

        templateRest = new RestTemplate();

    }
    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(puerto + "").concat("/inditex/api/");
    }

    @Test
    void prueba1_exitosa() {
        //Given
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00.00.00", formatoFecha);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23.59.59", formatoFecha);

        PriceDto priceDto = new PriceDto(ID_PRODUCTO, ID_MARCA, TASA_TARIFARIA_1, startDate, endDate, new BigDecimal("35.50"));

        // Call
        PriceDto response = templateRest.getForObject(baseUrl.concat(URL_TEST1), PriceDto.class);

        // Verify
        assertEquals(response, priceDto);
    }

    @Test
    void prueba1_falla() {
        // Given
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00.00.00", formatoFecha);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23.59.59", formatoFecha);

        PriceDto priceDto = new PriceDto(ID_PRODUCTO, ID_MARCA, TASA_TARIFARIA_1, startDate, endDate, new BigDecimal("37.50"));

        // Call
        PriceDto response = templateRest.getForObject(baseUrl.concat(URL_TEST1), PriceDto.class);

        // Verify
        assertNotEquals(response, priceDto);
    }

    @Test
    void prueba2_funcional() {
        // Given
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-15.00.00", formatoFecha);
        final LocalDateTime endDate = LocalDateTime.parse("2020-06-14-18.30.00", formatoFecha);

        PriceDto priceDto = new PriceDto(ID_PRODUCTO, ID_MARCA, TASA_TARIFARIA_2, startDate, endDate, new BigDecimal("25.45"));

        // Call
        PriceDto response = templateRest.getForObject(baseUrl.concat(URL_TEST2), PriceDto.class);

        // Verify
        assertEquals(response, priceDto);
    }

    @Test
    void prueba3_funcional() {
        // Given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00.00.00", formatoFecha);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23.59.59", formatoFecha);

        PriceDto priceDto = new PriceDto(ID_PRODUCTO, ID_MARCA, TASA_TARIFARIA_1, startDate, endDate, new BigDecimal("35.50"));

        // Call
        PriceDto response = templateRest.getForObject(baseUrl.concat(URL_TEST3), PriceDto.class);

        // Verify
        assertEquals(response, priceDto);
    }

    @Test
    void prueba4_funcional() {
        // Given
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-15-00.00.00", formatoFecha);
        final LocalDateTime endDate = LocalDateTime.parse("2020-06-15-11.00.00", formatoFecha);

        PriceDto priceDto = new PriceDto(ID_PRODUCTO, ID_MARCA, TASA_TARIFARIA_3, startDate, endDate, new BigDecimal("30.50"));

        // Call
        PriceDto response = templateRest.getForObject(baseUrl.concat(URL_TEST4), PriceDto.class);

        // Verify
        assertEquals(response, priceDto);
    }

    @Test
    void prueba5_funcional() {
        // Given
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-15-16.00.00", formatoFecha);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23.59.59", formatoFecha);

        PriceDto priceDto = new PriceDto(ID_PRODUCTO, ID_MARCA, TASA_TARIFARIA_4, startDate, endDate, new BigDecimal("38.95"));

        // Call
        PriceDto response = templateRest.getForObject(baseUrl.concat(URL_TEST5), PriceDto.class);

        // Verify
        assertEquals(response, priceDto);
    }

    @Test
    void prueba6_preciosInexistentesfalla() {
        // Call and Verify
        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            templateRest.getForObject(baseUrl.concat(TEST6_URL_NO_ENCONTRADA), PriceDto.class);
        });
    }

    @Test
    void prueba6_parametrosinvalidos_falla() {
        // Call and Verify
        assertThrows(HttpClientErrorException.UnprocessableEntity.class, () -> {
            templateRest.getForObject(baseUrl.concat(TEST6_PARAMETROS_NO_VALIDOS_URL), PriceDto.class);
        });
    }
}