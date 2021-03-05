package com.heliorodri.cloud.tema7.connection.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.heliorodri.cloud.tema7")
public class AppConfig {
    @Value("{twitterApi}")
    private String twitterApi;

    @Value("{githubApi}")
    private String githubApi;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
