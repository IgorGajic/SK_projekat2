package com.example.komponente_spring.client.notificationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class NotificationServiceClientConfiguration {
    @Bean
    public RestTemplate notificationServiceRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8083/api"));
        return restTemplate;
    }
}
