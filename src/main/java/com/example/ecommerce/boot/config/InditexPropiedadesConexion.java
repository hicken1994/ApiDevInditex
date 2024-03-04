package com.example.ecommerce.boot.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Component
@ConfigurationProperties
@Getter
@Setter
public class InditexPropiedadesConexion {

    private String dbType;

    private String dbDriverClass;

    private String dbUrl;

    private String dbUserName;

    private String dbPassword;

    private String dbTableName;
}
