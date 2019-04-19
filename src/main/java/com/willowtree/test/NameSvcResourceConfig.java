package com.willowtree.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NameSvcResourceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
