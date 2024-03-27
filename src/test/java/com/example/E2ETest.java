package com.example;

import com.example.ecommerce.api.Dto.PriceDto;
import com.example.ecommerce.boot.InditexAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.Constant.URL_TEMPLATE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = InditexAdapter.class)
public class E2ETest {
    @LocalServerPort
    public int port;
    private String baseUrl = "http://localhost";

    @Autowired
    private static TestRestTemplate restTemplate;
    @BeforeAll
    public static void init() {
        restTemplate = new TestRestTemplate();

    }
    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl + ":" + port + "/inditex/api/";
    }
    @Test
    public void testGetPricePerProduct() {

        // Startup
        Integer productId = 35455;
        Integer brandId = 1;

        // Call EndPoint ApiPrices
        ResponseEntity<PriceDto> responseEntity = restTemplate
                .getForEntity(baseUrl.concat(String.format(URL_TEMPLATE, "2020-06-14T10:00:00Z")), PriceDto.class, productId, brandId);

        // Return getStatusCode, and a PriceDto.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(productId);
    }
}