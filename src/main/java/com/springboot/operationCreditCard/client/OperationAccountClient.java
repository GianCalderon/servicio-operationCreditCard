package com.springboot.operationCreditCard.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.operationCreditCard.dto.OperationAccountDto;

import reactor.core.publisher.Flux;

@Service
public class OperationAccountClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationAccountClient.class);
	
	@Autowired
	@Qualifier("operationAccount")
	private WebClient client;

	public Flux<OperationAccountDto> operationAccount(String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		param.put("numberAccount", numberAccount);
		
		return client.get()
				.uri("/account/{numberAccount}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(OperationAccountDto.class);
		        
	}


}
