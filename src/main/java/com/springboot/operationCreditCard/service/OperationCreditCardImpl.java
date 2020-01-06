package com.springboot.operationCreditCard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.operationCreditCard.client.CreditCardClient;
import com.springboot.operationCreditCard.client.CurrentAccountClient;
import com.springboot.operationCreditCard.client.OperationAccountClient;
import com.springboot.operationCreditCard.client.SavingAccountClient;
import com.springboot.operationCreditCard.document.OperationCreditCard;
import com.springboot.operationCreditCard.dto.PaymentDto;
import com.springboot.operationCreditCard.dto.PaymentDto2;
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
		
		
		
		 return clientCard.findByNumberCard(purchaseDto.getNumberCard()).flatMap(card->{
			 
			 LOGGER.info("service 1: "+card.toString());
			 
			 return repo.save(convert.convertCreditCardPurchase(purchaseDto)).flatMap(ope->{
				 
				LOGGER.info("service 2: "+ope.toString());
				card.setAvailableBalance(card.getAvailableBalance()-ope.getAmountPayment());
				
				return clientCard.update(card, card.getId()).flatMap(g->{
					
					return Mono.just(ope);
				});

			 });

		 });
	}
	
	@Override
	public Mono<OperationCreditCard> paymentEfectivo(PaymentDto2 paymentDto2) {

		 return clientCard.findByNumberCard(paymentDto2.getNumberCard()).flatMap(card->{
			 
			 LOGGER.info("service 1: "+card.toString());
			 
			 return repo.save(convert.convertCreditCardPayment2(paymentDto2)).flatMap(ope->{
				 
				LOGGER.info("service 2: "+ope.toString());
				card.setAvailableBalance(card.getAvailableBalance()+ope.getAmountPayment());
				
				return clientCard.update(card, card.getId()).flatMap(g->{
					
					return Mono.just(ope);
				});

			 });

		 });
	}

  public Mono<OperationCreditCard> paymentAccount(PaymentDto paymentDto) {
		

	  return clientCard.findByNumberCard(paymentDto.getNumberCard()).flatMap(card->{
		  
		  return clientSavings.findByNumAccount(paymentDto.getNumberAccount()).flatMap(account->{
			  
			  account.setBalance(account.getBalance()-paymentDto.getAmountPayment());
			  card.setAvailableBalance(card.getAvailableBalance()+paymentDto.getAmountPayment());
			  
			  return clientSavings.update(account, account.getId()).flatMap(cuentaAct->{
				  
				  return clientCard.update(card, card.getId()).flatMap(g->{
						
				       return repo.save(convert.convertCreditCardPayment(paymentDto));
				  });
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
