package com.springboot.operationCreditCard.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.operationCreditCard.document.OperationCreditCard;
import com.springboot.operationCreditCard.dto.PaymentDto;
import com.springboot.operationCreditCard.dto.PurchaseDto;
import com.springboot.operationCreditCard.service.OperationCreditCardInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/operationCard")
public class OperationCreditCardController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationCreditCardController.class);


	  @Autowired
	  OperationCreditCardInterface service;
	  
//	  @Autowired
//	  SavingAccountClient  client;
	  
	 
	  

	  @GetMapping
	  public Mono<ResponseEntity<Flux<OperationCreditCard>>> toList() {

	    return Mono.just(ResponseEntity.ok()
	          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	  }

	  @GetMapping("/{id}")
	  public Mono<ResponseEntity<OperationCreditCard>> search(@PathVariable String id) {

		 LOGGER.info("NUMERO DE Id Controller :--->"+id);
		  
	    return service.findById(id).map(m -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(m))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @PutMapping("/{id}")
	  public Mono<ResponseEntity<OperationCreditCard>> update(@RequestBody  OperationCreditCard operationCreditCard,@PathVariable String id) {

	    return service.update(operationCreditCard, id)
	             .map(m -> ResponseEntity.created(URI.create("/api/OperationCreditCard".concat(m.getId())))
	             .contentType(MediaType.APPLICATION_JSON).body(m))
	             .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @DeleteMapping("/{id}")
	  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

	    return service.findById(id).flatMap(m -> {
	      return service.delete(m).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
	    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	  }
	  
	  
	  @PostMapping("/purchase")
	  public Mono<ResponseEntity<OperationCreditCard>> purchase(@RequestBody PurchaseDto purchaseDto) {
		  
		  LOGGER.info("controller :"+purchaseDto.toString());

	    return service.purchase(purchaseDto).map(m -> ResponseEntity.created(URI.create("/api/OperationCreditCard"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }
	  
	  @PostMapping("/payment")
	  public Mono<ResponseEntity<OperationCreditCard>> payment(@RequestBody PaymentDto paymentDto) {
		  
		  LOGGER.info("controller :"+paymentDto.toString());

	    return service.payment(paymentDto).map(m -> ResponseEntity.created(URI.create("/api/OperationCreditCard"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }


}
