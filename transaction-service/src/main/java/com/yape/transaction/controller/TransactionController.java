package com.yape.transaction.controller;

import com.yape.transaction.model.entity.Transaction;
import com.yape.transaction.model.request.TransactionRequest;
import com.yape.transaction.model.response.TransactionResponse;
import com.yape.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

    @GetMapping("/{transactionExternalId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TransactionResponse> getTransaction(@PathVariable String transactionExternalId) {
        return transactionService.getTransaction(transactionExternalId);
    }
}
