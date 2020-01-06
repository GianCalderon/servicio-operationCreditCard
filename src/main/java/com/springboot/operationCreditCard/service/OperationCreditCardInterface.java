package com.springboot.operationCreditCard.service;

import com.springboot.operationCreditCard.document.OperationCreditCard;
import com.springboot.operationCreditCard.dto.PaymentDto;
import com.springboot.operationCreditCard.dto.PaymentDto2;
import com.springboot.operationCreditCard.dto.PurchaseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationCreditCardInterface {

	  public Flux<OperationCreditCard> findAll();
	  
	  public Mono<OperationCreditCard> findById(String id);
	  
	  public Mono<OperationCreditCard> save(OperationCreditCard OperationCreditCard);
	  
	  public Mono<OperationCreditCard> purchase(PurchaseDto purchaseDto);
	  
	  public Mono<OperationCreditCard> paymentAccount(PaymentDto paymentDto);
	  
	  public Mono<OperationCreditCard> paymentEfectivo(PaymentDto2 paymentDto2);

	  public Mono<OperationCreditCard> update(OperationCreditCard OperationCreditCard,String id);
	  
	  public Mono<Void> delete(OperationCreditCard OperationCreditCard);
	  
	  public Flux<OperationCreditCard> findByNumberCard(String numberCard);
	  

}
