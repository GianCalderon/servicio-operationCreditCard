package com.springboot.operationCreditCard.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.operationCreditCard.dto.CreditCardDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardClient.class);
	
//	@Autowired
//	private WebClient client;

WebClient client = WebClient.create("http://localhost:8008/api/creditCard");
	
	public Flux<CreditCardDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(CreditCardDto.class));
	}


	public Mono<CreditCardDto> findById(String id) {
		
		LOGGER.info("NUMERO DE Id en client :--->"+id);
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", id);
		
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(response ->response.bodyToMono(CreditCardDto.class));
		
		
		        
	}


	
	public Mono<CreditCardDto> save(CreditCardDto CreditCardDto) {
		
		LOGGER.info("listo a enviar: "+CreditCardDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(CreditCardDto))
			   .retrieve()
			   .bodyToMono(CreditCardDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<CreditCardDto> update(CreditCardDto CreditCardDto, String id) {		
		
		
		LOGGER.info("listo a enviar: "+CreditCardDto.toString()+"ID --> :"+id);
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",id))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(CreditCardDto)
				   .retrieve()
				   .bodyToMono(CreditCardDto.class);
	}
	
	public Mono<CreditCardDto> findByNumberCard(String numberCard) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberCard);
		
		return client.get()
				.uri("/tarjeta/{numCuenta}",Collections.singletonMap("numCuenta",numberCard))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CreditCardDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

}
