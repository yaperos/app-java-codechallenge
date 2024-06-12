package com.yape.transaction.usecases;

import com.yape.transaction.usecases.in.CreateTransactionInputBoundary;
import com.yape.transaction.usecases.models.CreateTransactionModel;
import com.yape.transaction.usecases.out.AntifraudGateway;
import com.yape.transaction.entities.enums.TransactionStatus;
import com.yape.transaction.entities.enums.TransactionType;
import com.yape.transaction.usecases.out.TransactionDataAccessGateway;
import com.yape.transaction.entities.models.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class CreateTransactionUseCase implements CreateTransactionInputBoundary {

    private final TransactionDataAccessGateway transactionDataAccessGateway;
    private final AntifraudGateway antifraudGateway;

    public CreateTransactionUseCase(TransactionDataAccessGateway transactionDataAccessGateway, AntifraudGateway antifraudGateway) {
        this.transactionDataAccessGateway = transactionDataAccessGateway;
        this.antifraudGateway = antifraudGateway;
    }

    @Override
    @Transactional
    public Transaction createTransaction(CreateTransactionModel createTransactionModel) {
        log.info("CreateTransactionUseCase createTransaction createTransactionModel {}", createTransactionModel);
        Transaction transaction = createTransactionModel.convertEntity();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionExternalId(UUID.randomUUID());
        transaction.setCreateAt(LocalDateTime.now());
        antifraudGateway.validate(transaction);
        return transactionDataAccessGateway.create(transaction);
    }
}
