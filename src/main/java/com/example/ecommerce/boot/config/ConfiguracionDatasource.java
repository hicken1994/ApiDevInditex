package com.example.ecommerce.boot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class ConfiguracionDatasource {

    private final InditexPropiedadesConexion inditexConnectProperties;

    @Bean
    public DataSource getDataSource() {

        log.info("[CONNECTION] url: <{}> ", inditexConnectProperties.getDbUrl());

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(inditexConnectProperties.getDbDriverClass());
        dataSourceBuilder.url(inditexConnectProperties.getDbUrl());
        dataSourceBuilder.username(inditexConnectProperties.getDbUserName());
        dataSourceBuilder.password(inditexConnectProperties.getDbPassword());

        return dataSourceBuilder.build();
    }
}