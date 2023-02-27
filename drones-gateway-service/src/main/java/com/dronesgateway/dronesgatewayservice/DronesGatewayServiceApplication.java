package com.dronesgateway.dronesgatewayservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class DronesGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesGatewayServiceApplication.class, args);
	}

}
