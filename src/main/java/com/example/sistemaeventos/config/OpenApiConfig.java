package com.example.sistemaeventos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "API de Venda de Ingressos",
                version = "1.0",
                description = "API de Venda de Ingressos para Eventos"
        )
)
public class OpenApiConfig {
}
