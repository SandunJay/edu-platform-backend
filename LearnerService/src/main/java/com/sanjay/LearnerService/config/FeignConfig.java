package com.sanjay.LearnerService.config;

import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public ApacheHttpClient feignClient() {
        return new ApacheHttpClient();
    }
}
