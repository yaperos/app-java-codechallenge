package com.aly.antifraudes_service.controller;

import com.aly.antifraudes_service.exception.ModeloNotFoundException;
import com.aly.antifraudes_service.model.dtos.TransactionExternalRequest;
import com.aly.antifraudes_service.services.InterfaceAntiFrauConsultService;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/antifraudes")
public class AntiFrauConsultController {
	private static final Logger log = LoggerFactory.getLogger(AntiFrauConsultController.class);

	@Autowired
	InterfaceAntiFrauConsultService interfaceAntiFrauConsultService;

	@PostMapping(value = "consultAntiFrau", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TransactionExternalRequest> consultAntiFrau(@Validated @RequestBody TransactionExternalRequest transactionExternalRequest)
			throws ModeloNotFoundException {
		return new ResponseEntity<TransactionExternalRequest>(interfaceAntiFrauConsultService.consultAntiFrau(transactionExternalRequest), HttpStatus.OK);
	}

}
