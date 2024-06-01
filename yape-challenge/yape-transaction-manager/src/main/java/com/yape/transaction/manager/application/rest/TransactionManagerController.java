package com.yape.transaction.manager.application.rest;

import com.yape.transaction.manager.application.response.FindTransactionResponse;
import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/yape/transaction/v1")
public class TransactionManagerController {

    private final TransactionService service;

    @Autowired
    public TransactionManagerController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<FindTransactionResponse> create(@RequestBody Transaction request) {

        return ResponseEntity.ok().body(service.createTransaction(request).toRestFindResponse());
    }

    @GetMapping("/{externalId}")
    public ResponseEntity<List<FindTransactionResponse>> find(@PathVariable String externalId) {

        List<Transaction> transaction = service.findTransactionsByExternalId(externalId);

        return ResponseEntity.ok().body(transaction.stream().map(Transaction::toRestFindResponse).collect(Collectors.toList()));
    }
}
