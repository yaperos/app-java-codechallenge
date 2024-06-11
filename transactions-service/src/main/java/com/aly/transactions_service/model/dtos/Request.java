package com.aly.transactions_service.model.dtos;

import lombok.Data;

@Data
public class Request {
	private String accountExternalIdDebit;
	private String accountExternalIdCredit;
	private Long tranferTypeId;
	private Integer value;
}
