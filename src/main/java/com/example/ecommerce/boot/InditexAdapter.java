package com.example.ecommerce.boot;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories("com.example.ecommerce.persistence.repository")
@EntityScan("com.example.ecommerce.domain.entities")
@Slf4j
@ComponentScan(basePackages = {"com.example.ecommerce"})
public class InditexAdapter {

    @Autowired
    private TestDataConfigService testDataConfigService;

    public static void main(String[] args) {
        SpringApplication.run(InditexAdapter.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(InditexAdapter::shutdown));
        log.info("===================== AdaptadorInditex Iniciando ========================");
    }

    private static void shutdown() {
        log.info("===================== AdaptadorInditex Detenido ========================");
    }

    @PostConstruct
    private void initDb() {
        log.info("****** Insertando datos de prueba ******");
        testDataConfigService.insertarDatosPrueba();
        log.info("****** Datos insertados correctamente ******");
    }
}