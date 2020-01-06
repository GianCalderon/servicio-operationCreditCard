package com.springboot.operationCreditCard.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.operationCreditCard.document.OperationCreditCard;
import com.springboot.operationCreditCard.dto.PaymentDto;
import com.springboot.operationCreditCard.dto.PaymentDto2;
import com.springboot.operationCreditCard.dto.PurchaseDto;

@Component
public class UtilConvert {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public OperationCreditCard convertCreditCardPurchase(PurchaseDto purchaseDto) {

		OperationCreditCard  operationCreditCard = new OperationCreditCard();
		
		operationCreditCard.setAmountPayment(purchaseDto.getAmountPayment());
		operationCreditCard.setNumberCard(purchaseDto.getNumberCard());
		operationCreditCard.setNumOperation(String.valueOf((int)(Math.random()*9999+1)));
		operationCreditCard.setTypePayment("Compra");
		operationCreditCard.setDateCreate(new Date());
		operationCreditCard.setDateUpdate(new Date());
		
		LOGGER.info("convert  :"+operationCreditCard.toString());
		
		return operationCreditCard;

	}

	public OperationCreditCard convertCreditCardPayment(PaymentDto paymentDto) {

		OperationCreditCard  operationCreditCard = new OperationCreditCard();

		operationCreditCard.setAmountPayment(paymentDto.getAmountPayment());
		operationCreditCard.setNumberCard(paymentDto.getNumberCard());
		operationCreditCard.setNumOperation(String.valueOf((int)(Math.random()*9999+1)));
		operationCreditCard.setTypePayment("Pago-Cuenta-Bancaria");
		operationCreditCard.setDateCreate(new Date());
		operationCreditCard.setDateUpdate(new Date());
		

		LOGGER.info("Convert :"+operationCreditCard.toString());
		return operationCreditCard;

	}
	
	
	public OperationCreditCard convertCreditCardPayment2(PaymentDto2 paymentDto2) {

		OperationCreditCard  operationCreditCard = new OperationCreditCard();

		operationCreditCard.setAmountPayment(paymentDto2.getAmountPayment());
		operationCreditCard.setNumberCard(paymentDto2.getNumberCard());
		operationCreditCard.setNumOperation(String.valueOf((int)(Math.random()*9999+1)));
		operationCreditCard.setTypePayment("Pago-Efectivo");
		operationCreditCard.setDateCreate(new Date());
		operationCreditCard.setDateUpdate(new Date());
		

		LOGGER.info("Convert :"+operationCreditCard.toString());
		return operationCreditCard;

	}
	
	

	


}
