package com.sanjay.LearnerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.sanjay.LearnerService.client"})
public class LearnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnerServiceApplication.class, args);
	}

}
