package com.aly.transactions_service.controllers;
import com.aly.transactions_service.exception.ModeloNotFoundException;
import com.aly.transactions_service.model.dtos.Request;
import com.aly.transactions_service.model.entities.TransactionExternal;
import com.aly.transactions_service.model.entities.TransactionType;
import com.aly.transactions_service.services.InterfaceTransactionExternalService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@ApiResponse(description = "rest Services for transaction external")
public class TransactionExternalController {
	private static final Logger log = LoggerFactory.getLogger(TransactionExternalController.class);

	@Autowired
	InterfaceTransactionExternalService interfaceTransactionExternalService;

	@GetMapping(value = "/list", produces = "application/json")
	public ResponseEntity<List<TransactionExternal>> list() throws ModeloNotFoundException {
		return new ResponseEntity<List<TransactionExternal>>(interfaceTransactionExternalService.list(), HttpStatus.OK);
	}

	@GetMapping(value = "/list/{id}", produces = "application/json")
	public ResponseEntity<Optional<TransactionExternal>> list(@PathVariable("id") Long id) throws ModeloNotFoundException {
		return new ResponseEntity<Optional<TransactionExternal>>(interfaceTransactionExternalService.listId(id), HttpStatus.OK);
	}

	@PostMapping(value = "saveTransactionExternal", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TransactionExternal> saveTransactionExternal(@Valid @RequestBody Request request)
			throws ModeloNotFoundException {
		return new ResponseEntity<TransactionExternal>(interfaceTransactionExternalService.saveTransactionExternal(request), HttpStatus.OK);
	}

	@DeleteMapping(value = "/eliminar/{id}", produces = "application/json")
	public void eliminar(@PathVariable("id") Long id) {
		Optional<TransactionExternal> existePaciente = interfaceTransactionExternalService.listId(id);
		if (existePaciente.equals(Optional.empty())) {
			throw new ModeloNotFoundException("id: " + id);
		}
		interfaceTransactionExternalService.delete(id);
	}
}
