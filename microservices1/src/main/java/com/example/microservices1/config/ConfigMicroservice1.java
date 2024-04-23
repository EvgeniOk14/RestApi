package com.example.microservices1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigMicroservice1
{
    private final ApplicationContext applicationContext;
        

    public ConfigMicroservice1(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}


