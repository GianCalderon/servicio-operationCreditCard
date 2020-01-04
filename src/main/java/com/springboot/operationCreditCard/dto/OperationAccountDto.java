package com.springboot.operationCreditCard.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OperationAccountDto {
	
	private String id;
	private String numDoc;
	private String numberAccount;
	private String numOperation;
	private String typeOperation;
	private Double amount;
	private Double commission;
	private Date dateOperation;


}
