package com.yape.transaction.manager.application.graphql;

import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.repository.TransactionRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    Iterable<Transaction> transactions() {
        return repository.findAll();
    }
}
