package com.aly.antifraudes_service.model.dtos;

import com.aly.antifraudes_service.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.Data;

@Data
public class TransactionExternalRequest {

	private Long id;
	private TransactionTypeRequest transactionType;
	private TransactionStatusRequest transactionStatus;
	private Integer value;
	private String userCreation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.FORMATO_FECHA_HORA, timezone = Constant.ZONA_HORARIA)
	private Date dateCreation;
	private String userUpdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.FORMATO_FECHA_HORA, timezone = Constant.ZONA_HORARIA)
	private Date dateUpdate;
}
