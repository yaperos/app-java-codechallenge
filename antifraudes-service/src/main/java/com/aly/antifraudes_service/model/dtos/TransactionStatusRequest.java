package com.aly.antifraudes_service.model.dtos;


import lombok.Data;

@Data
public class TransactionStatusRequest {

	private Long id;
	private String code;
	private String name;

}
