package com.example.microservice2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig
{
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
