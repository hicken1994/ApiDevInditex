package com.example;

import com.example.ecommerce.Dto.PriceDto;
import com.example.ecommerce.controller.PriceController;
import com.example.ecommerce.service.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class PriceControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void getPrice_Test1() throws Exception {
        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(35455L);
        priceDto.setBrandId(1L);
        priceDto.setPriceList(1);
        priceDto.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceDto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDto.setPrice(35.50);
        priceDto.setCurrency("EUR");

        when(priceService.getPriceByParameters(any(), any(), any())).thenReturn(priceDto);

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void getPrice_Test2() throws Exception {
        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(35455L);
        priceDto.setBrandId(1L);
        priceDto.setPriceList(1);
        priceDto.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceDto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDto.setPrice(25.45);
        priceDto.setCurrency("EUR");

        when(priceService.getPriceByParameters(any(), any(), any())).thenReturn(priceDto);

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void getPrice_Test3() throws Exception {
        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(35455L);
        priceDto.setBrandId(1L);
        priceDto.setPriceList(1);
        priceDto.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceDto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDto.setPrice(35.50);
        priceDto.setCurrency("EUR");

        when(priceService.getPriceByParameters(any(), any(), any())).thenReturn(priceDto);

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    public void getPrice_Test4() throws Exception {
        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(35455L);
        priceDto.setBrandId(1L);
        priceDto.setPriceList(1);
        priceDto.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        priceDto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDto.setPrice(30.50);
        priceDto.setCurrency("EUR");

        when(priceService.getPriceByParameters(any(), any(), any())).thenReturn(priceDto);

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    @Test
    @DisplayName("Test 5: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void getPrice_Test5() throws Exception {
        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(35455L);
        priceDto.setBrandId(1L);
        priceDto.setPriceList(1);
        priceDto.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        priceDto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDto.setPrice(38.95);
        priceDto.setCurrency("EUR");

        when(priceService.getPriceByParameters(any(), any(), any())).thenReturn(priceDto);

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-15T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
}
