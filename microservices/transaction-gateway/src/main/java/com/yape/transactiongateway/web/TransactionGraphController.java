package com.yape.transactiongateway.web;

import com.yape.transactiongateway.model.dto.RegisterTransactionRequest;
import com.yape.transactiongateway.model.dto.TransactionResponse;
import com.yape.transactiongateway.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TransactionGraphController {

    private final TransactionService transactionService;

    @QueryMapping
    public Flux<TransactionResponse> getAllTransactions(@Argument Integer limit, @Argument Integer offset) {
        log.info("Iniciando consulta de transacciones. Limit: {} Offset {}", limit, offset);
        return transactionService.getAllTransactions(limit, offset);
    }

    @QueryMapping
    public Mono<TransactionResponse> getTransactionByCode(@Argument String transactionCode) {
        log.info("Iniciando busqueda de la transaccion con codigo {}", transactionCode);
        return transactionService.getTransactionByCode(transactionCode);
    }

    @MutationMapping
    public Mono<Void> createTransaction(@Argument RegisterTransactionRequest transactionBody) {
        log.info("Creando una nueva transaccion");
        return transactionService.createTransaction(transactionBody);
    }

}
