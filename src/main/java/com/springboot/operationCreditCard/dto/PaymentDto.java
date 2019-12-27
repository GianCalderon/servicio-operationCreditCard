package com.springboot.operationCreditCard.dto;

import lombok.Data;

@Data
public class PaymentDto {
	
	private String numberCard;
	private Double amountPayment;
	private String typePayment;
	private String numberAccount;

}
