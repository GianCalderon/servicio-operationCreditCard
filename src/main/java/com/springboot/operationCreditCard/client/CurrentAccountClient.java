package com.springboot.operationCreditCard.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.operationCreditCard.dto.CurrentAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountClient.class);
	
	@Autowired
	@Qualifier("currentAccount")
	private WebClient client;


	public Flux<CurrentAccountDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(CurrentAccountDto.class));
	}


	public Mono<CurrentAccountDto> findById(String id) {
		
		LOGGER.info("NUMERO DE Id en client :--->"+id);
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", id);
		
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(response ->response.bodyToMono(CurrentAccountDto.class));
		
		
		        
	}


	
	public Mono<CurrentAccountDto> save(CurrentAccountDto currentAccountDto) {
		
		LOGGER.info("listo a enviar: "+currentAccountDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(currentAccountDto))
			   .retrieve()
			   .bodyToMono(CurrentAccountDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<CurrentAccountDto> update(CurrentAccountDto currentAccountDto, String id) {		
		
		
		LOGGER.info("listo a enviar: "+currentAccountDto.toString()+"ID --> :"+id);
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",id))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(currentAccountDto)
				   .retrieve()
				   .bodyToMono(CurrentAccountDto.class);
	}
	
	public Mono<CurrentAccountDto> findByNumAccount(String numCuenta) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numCuenta);
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		param.put("numCuenta", numCuenta);
		
		return client.get().uri("/cuenta/{numCuenta}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CurrentAccountDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

}
