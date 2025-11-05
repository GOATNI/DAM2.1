package org.iesch.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MiConfig {

    @Bean
    public RestTemplate  restTemplate(){
        return new RestTemplate();
    }
}
