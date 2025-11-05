package org.iesch.ad.Excepciones.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    public void addCrosMapping(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost")
                .allowedMethods("GET","PUT")
                .allowCredentials(true);
    }
}
