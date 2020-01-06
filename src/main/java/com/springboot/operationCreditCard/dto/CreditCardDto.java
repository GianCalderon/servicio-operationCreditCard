package com.springboot.operationCreditCard.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CreditCardDto {
	
	
	private String id;
	private String numDoc;
	private String nameCard;
	private String numberCard;
	private String codeSecurity;
	private Double balance;
	private Double availableBalance;
	private String tea;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateExpiration;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateCreate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateUpdate;
	

}
