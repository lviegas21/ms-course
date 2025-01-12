package com.example.hr_payroll.config;

import com.example.hr_payroll.models.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateWorker {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
