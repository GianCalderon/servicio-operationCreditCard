package com.springboot.operationCreditCard.document;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection ="Operaciones-Tarjeta")
public class OperationCreditCard {
	
	@Id
	private String id;
	
	@NotNull(message = "OperationCreditCard numberCard must not be null")
	private String numberCard;
	
	@NotNull(message = "OperationCreditCard numOperation must not be null")
	@Indexed(unique = true)
	private String numOperation;
	
	@NotNull(message = "OperationCreditCard typeOperation must not be null")
	private String typePayment;
	
	@NotNull(message = "OperationCreditCard amountOperation must not be null")
	private Double amountPayment;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateCreate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateUpdate;
	

	

}
