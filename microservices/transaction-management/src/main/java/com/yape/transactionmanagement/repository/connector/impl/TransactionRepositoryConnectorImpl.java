package com.yape.transactionmanagement.repository.connector.impl;

import com.yape.transactionmanagement.model.entity.TransactionEntity;
import com.yape.transactionmanagement.repository.TransactionRepository;
import com.yape.transactionmanagement.repository.connector.TransactionRepositoryConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionRepositoryConnectorImpl implements TransactionRepositoryConnector {

    private final TransactionRepository transactionRepository;

    @Override
    public Mono<List<TransactionEntity>> findAllTransactions() {
        return Mono.fromFuture(CompletableFuture.supplyAsync(transactionRepository::callGetTransactionsSP))
            .doOnSubscribe(sub -> log.info("Buscando transacciones"))
            .doOnSuccess(success -> log.info("Busqueda exitosa"))
            .doOnError(err -> log.info("Ocurrio un error en la busqueda de todas las transacciones"));
    }

    @Override
    public Mono<TransactionEntity> findTransactionByCode(String transactionCode) {
        return Mono.fromFuture(CompletableFuture.supplyAsync(() -> transactionRepository.callGetTransactionByCode(transactionCode)))
            .doOnSubscribe(sub -> log.info("Buscando transaccion {}", transactionCode))
            .doOnSuccess(success -> log.info("Busqueda exitosa"))
            .doOnError(err -> log.info("Ocurrio un error en la busqueda de la transaccion"));
    }

    @Override
    public Mono<TransactionEntity> saveTransaction(TransactionEntity entity) {
        return Mono.fromFuture(CompletableFuture.supplyAsync(() -> transactionRepository.save(entity)))
            .doOnSubscribe(sub -> log.info("Registrando nueva transaccion"))
            .doOnSuccess(success -> log.info("Transaccion se registro correctamente"))
            .doOnError(err -> log.info("Ocurrio un error al intentar registrar la transaccion"));
    }

}
