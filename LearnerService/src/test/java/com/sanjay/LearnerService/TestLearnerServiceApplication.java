package com.sanjay.LearnerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestLearnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(LearnerServiceApplication::main).with(TestLearnerServiceApplication.class).run(args);
	}

}
