package com.example;

import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

public final class Constant {

    public static RestTemplate templateRest = null;



    public static final String URL_TEMPLATE = "price?brandId=1&productId=35455&priceDate=%s";

    public static final  String TEST6_URL_NO_ENCONTRADA = "price?brandId=2&productId=35455&priceDate=2020-06-16T21:00:00Z";
    public static final int ID_PRODUCT = 35455;
    public static final int ID_BRAND = 1;
    public static final int RATE_TARIFF_1 = 1;
    public static final int RATE_TARIFF_2 = 2;
    public static final int RATE_TARIFF_3 = 3;
    public static final int RATE_TARIFF_4 = 4;
    public static final DateTimeFormatter dateFormats = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");


}
