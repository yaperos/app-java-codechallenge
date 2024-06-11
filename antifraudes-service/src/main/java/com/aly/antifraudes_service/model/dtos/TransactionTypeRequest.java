package com.aly.antifraudes_service.model.dtos;


import lombok.Data;

@Data
public class TransactionTypeRequest {

	private Long id;
	private String code;
	private String name;

}
