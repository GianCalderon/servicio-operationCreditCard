package com.springboot.operationCreditCard.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	
	@Bean("creditCard")
	public WebClient registrarWebCrediCard() {
		
		return WebClient.create("http://localhost:8008/api/creditCard");
		//return WebClient.create("http://gateway-server:9090/api/creditCard");

	}

	@Bean("currentAccount")
	public WebClient registrarWebCurrentAccount() {
		
		return WebClient.create("http://localhost:8004/api/currentAccount");
		//return WebClient.create("http://gateway-server:9090/api/currentAccount");
		
	}
	@Bean("savingsAccount")
	public WebClient registrarWebSavingsAccount() {
		
		return WebClient.create("http://localhost:8003/api/savingsAccount");
		//return WebClient.create("http://gateway-server:9090/api/enterprise");
				
		
	}
	@Bean("operationAccount")
	public WebClient registrarOperationAccount() {
		
		return WebClient.create("http://localhost:8011/api/operationAccount");
		//return WebClient.create("http://gateway-server:9090/api/operationAccount");
		

	}
	


}
