package com.example.ecommerce.boot;

import com.example.ecommerce.domain.entities.PriceProduct;
import com.example.ecommerce.persistence.repository.PriceProductRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
@Service
public class TestDataConfigService {
    private static final String DATA_FILE_PATH = "src/main/resources/prices.csv";

    private final PriceProductRepository priceProductRepository;

    public TestDataConfigService(PriceProductRepository priceProductRepository) {
        this.priceProductRepository = priceProductRepository;
    }

    public void insertarDatosPrueba() {
        List<PriceProduct> precioList = processInputFile(DATA_FILE_PATH);
        priceProductRepository.saveAll(precioList);
    }

    private List<PriceProduct> processInputFile(String inputFilePath) {
        List<PriceProduct> inputList = new ArrayList<>();
        try {
            InputStream inputFS = new FileInputStream(new File(inputFilePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            inputList = br.lines().skip(1).map(convertCSVToPrecio).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            log.error("Hubo un error leyendo el archivo: {}", e.getMessage());
        }
        return inputList;
    }

    private static final Function<String, PriceProduct> convertCSVToPrecio = (line) -> {
        String[] p = line.split(",");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        return PriceProduct.builder()
                .productId(Integer.parseInt(p[4]))
                .brandId(Integer.parseInt(p[0]))
                .priceList(Integer.parseInt(p[3]))
                .priority(Integer.parseInt(p[5]))
                .startDate(LocalDateTime.parse(p[1], formatoFecha))
                .endDate(LocalDateTime.parse(p[2], formatoFecha))
                .price(BigDecimal.valueOf(Double.parseDouble(p[6])))
                .lastUpdateDate(LocalDateTime.parse(p[8], formatoFecha))
                .lastUpdateBy(p[9])
                .curr(p[7])
                .build();
    };
}