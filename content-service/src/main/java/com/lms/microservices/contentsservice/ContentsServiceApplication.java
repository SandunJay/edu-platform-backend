package com.lms.microservices.contentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentsServiceApplication.class, args);
	}

}
