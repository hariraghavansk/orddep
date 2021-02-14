package com.sl.ms.ordermanagement;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableCircuitBreaker
@Configuration
@EnableSwagger2
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTempalte()
	{
		return new RestTemplate();
	}
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails());
	}
	
	public ApiInfo apiDetails() {
		return new ApiInfo(
				"Order Management API",
				"Order services",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Hariraghavan SK","http://Localhost:8888/dev/api","hsk@ms.com"),
				"API License",
				"http://Localhost:8888/dev/api",
				Collections.emptyList());
							
	}
	
}
