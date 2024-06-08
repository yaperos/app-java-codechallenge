package com.yape.transactionmanagement.service.impl;

import com.yape.transactionmanagement.builder.TransactionBuilder;
import com.yape.transactionmanagement.kafka.producer.TransactionMessageProducer;
import com.yape.transactionmanagement.model.constant.TransactionStatusEnum;
import com.yape.transactionmanagement.model.request.RegisterTransactionRequest;
import com.yape.transactionmanagement.model.response.TransactionResponse;
import com.yape.transactionmanagement.repository.connector.TransactionRepositoryConnector;
import com.yape.transactionmanagement.service.TransactionManagementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionManagementServiceImpl implements TransactionManagementService {

    private final TransactionRepositoryConnector transactionRepositoryConnector;
    private final TransactionMessageProducer transactionMessageProducer;

    @Cacheable(cacheNames = "transactions")
    @Override
    public Flux<TransactionResponse> getAllTransactions() {
        return transactionRepositoryConnector.findAllTransactions()
            .flatMapMany(Flux::fromIterable)
            .onBackpressureBuffer()
            .map(TransactionBuilder::buildTransactionResponse);
    }

    @Cacheable(cacheNames = "transaction", key = "#txCode", unless = "#result == null")
    @Override
    public Mono<TransactionResponse> getTransactionByCode(String txCode) {
        return transactionRepositoryConnector.findTransactionByCode(txCode)
            .map(TransactionBuilder::buildTransactionResponse);
    }

    @CacheEvict(cacheNames = "transactions", allEntries = true)
    @Transactional
    @Override
    public Mono<Void> registerTransaction(RegisterTransactionRequest request) {
        return Mono.fromCallable(() -> TransactionBuilder.buildTransactionEntity(request))
            .flatMap(entity -> transactionRepositoryConnector.saveTransaction(entity)
                .flatMap(sEntity -> Mono.fromCallable(() -> transactionMessageProducer.sendMessage(TransactionBuilder.buildTransactionDTO(sEntity)))
                    .onErrorResume(err -> {
                        log.error("Updating state to REJECTED because can't evaluate transaction value. TxCode {}", sEntity.getTransactionCode());
                        sEntity.setTransactionStatus(TransactionStatusEnum.REJECTED.getDescription());
                        return transactionRepositoryConnector.saveTransaction(entity)
                            .thenReturn(Boolean.FALSE);
                    })))
            .then();
    }

}
