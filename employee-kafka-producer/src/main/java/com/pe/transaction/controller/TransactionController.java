package com.pe.transaction.controller;

import com.pe.transaction.model.api.TransactionRequest;
import com.pe.transaction.model.api.TransactionResponse;
import com.pe.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class: EmployeeController.
 * @author Relari
 */

@RestController
@RequestMapping(path = "${application.api.path}")
public class TransactionController {


    @Autowired
    TransactionService service;


  //  private KafkaTemplate<String, Transaction> kafkaTemplate;

    private static final String TOPIC = "transaction-topic";

    @PostMapping("/create")
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transaction) {
        return service.saveTransaction(transaction);
    }

    @GetMapping("/find/{transactionExternalId}")
    public TransactionResponse findTransaction(@PathVariable("transactionExternalId") String transactionExternalId) {
        return service.getTransaction(transactionExternalId);
    }


}
