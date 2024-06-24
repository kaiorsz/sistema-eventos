package com.example.sistemaeventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class
        }
)
public class SistemaEventosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaEventosApplication.class, args);
    }

}
