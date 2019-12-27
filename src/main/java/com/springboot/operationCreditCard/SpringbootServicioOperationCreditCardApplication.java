package com.springboot.operationCreditCard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioOperationCreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOperationCreditCardApplication.class, args);
	}

}
