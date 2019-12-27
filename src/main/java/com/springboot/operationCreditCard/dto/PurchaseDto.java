package com.springboot.operationCreditCard.dto;

import lombok.Data;

@Data
public class PurchaseDto {
	
	private String numberCard;
	private Double amountPayment;

}
