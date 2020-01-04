package com.springboot.operationCreditCard.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.operationCreditCard.client.CreditCardClient;
import com.springboot.operationCreditCard.client.CurrentAccountClient;
import com.springboot.operationCreditCard.client.OperationAccountClient;
import com.springboot.operationCreditCard.client.SavingAccountClient;
import com.springboot.operationCreditCard.document.OperationCreditCard;
import com.springboot.operationCreditCard.dto.OperationAccountDto;
import com.springboot.operationCreditCard.dto.PaymentDto;
import com.springboot.operationCreditCard.dto.PurchaseDto;
import com.springboot.operationCreditCard.repo.OperationCreditCardRepo;
import com.springboot.operationCreditCard.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationCreditCardImpl implements OperationCreditCardInterface {
	
	
	@Autowired
	OperationCreditCardRepo repo;
	
	@Autowired
	CreditCardClient clientCard;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	SavingAccountClient clientSavings;
	
	@Autowired
	CurrentAccountClient clientCurrent;
	
	@Autowired
	OperationAccountClient clientOperationAccount;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationCreditCardImpl.class);
	
	@Override
	public Flux<OperationCreditCard> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Mono<OperationCreditCard> findById(String id) {

		return repo.findById(id);
	}

	
	@Override
	public Mono<OperationCreditCard> purchase(PurchaseDto purchaseDto) {
		
		LOGGER.info("service 1: "+purchaseDto.toString());

	   return clientCard.findByNumberCard(purchaseDto.getNumberCard()).flatMap(tarjeta->{
		   
		   LOGGER.info("service 2: "+tarjeta.toString());
		 
		   
		   return repo.save(convert.convertCreditCardPurchase(purchaseDto)).flatMap(operacion ->{
			   
			   LOGGER.info("service 3: "+operacion.toString());
			
			   clientCard.update(tarjeta, tarjeta.getId()).block(); 
			    
			   return Mono.just(operacion);
		   });
		   
		 
		   
	   });
	}
	
	@Override
	public Mono<OperationCreditCard> payment(PaymentDto paymentDto) {
		
		LOGGER.info("service 1: "+paymentDto.toString());
		

	  return clientCard.findByNumberCard(paymentDto.getNumberCard()).flatMap(tarjeta->{

		  return clientSavings.findByNumAccount(paymentDto.getNumberAccount()).flatMap(cuenta ->{

			      return repo.save(convert.convertCreditCardPayment(paymentDto)).flatMap(operacion ->{
	
			    	  Double comision=0.0;
			    	 
					 if(cuenta.getIdOperation().size()>5) comision=10.0;
				  
				      LOGGER.info("service 2: "+operacion.toString());
				
					  cuenta.setBalance((cuenta.getBalance()+operacion.getAmountPayment()));
		  		      cuenta.getIdOperation().add(operacion.getId());
		  		      cuenta.setUpdateDate(new Date());
		  		      clientSavings.update(cuenta,cuenta.getId()).block();
					  
					  return Mono.just(operacion);
	
			  });


		  });
		  
		
	  });

	}
	


	@Override
	public Mono<Void> delete(OperationCreditCard OperationCreditCard) {
		// TODO Auto-generated method stub
		return repo.delete(OperationCreditCard);
	}
	

	@Override
	public Mono<OperationCreditCard> update(OperationCreditCard OperationCreditCard, String id) {
		// TODO Auto-generated method stub
	    return repo.findById(id).flatMap(operacion -> {

	    	operacion.setNumberCard(OperationCreditCard.getNumberCard());
	    	operacion.setNumOperation(OperationCreditCard.getNumOperation());
	    	operacion.setTypePayment(OperationCreditCard.getTypePayment());
	    	operacion.setAmountPayment(OperationCreditCard.getAmountPayment());
	        
	        return repo.save(operacion);

	      });
	
	}
	
	public Flux<OperationCreditCard> findByNumberCard(String numberCard) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberCard);

		return repo.findByNumberCard(numberCard);
			
	
	}

	@Override
	public Mono<OperationCreditCard> save(OperationCreditCard OperationCreditCard) {
		
		return repo.save(OperationCreditCard);
	}
	

	
	

	


}
