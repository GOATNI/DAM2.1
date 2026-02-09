package org.iesch.ad.DocumentosReferenciados.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Documentos Referenciados API")
                        .version("1.0")
                        .description("API para gestionar documentos referenciados con MongoDB"));
    }
}
