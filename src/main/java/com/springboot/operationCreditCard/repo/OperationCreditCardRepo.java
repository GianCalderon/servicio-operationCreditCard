package com.springboot.operationCreditCard.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.operationCreditCard.document.OperationCreditCard;

import reactor.core.publisher.Flux;

@Repository
public interface OperationCreditCardRepo extends ReactiveMongoRepository<OperationCreditCard, String> {
	
	
	Flux<OperationCreditCard> findByNumberCard(String numberCard);

	
	


}
