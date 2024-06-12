package com.yape.transaction.usecases;

import com.yape.transaction.usecases.in.UpdateTransactionInputBoundary;
import com.yape.transaction.usecases.models.UpdateTransactionModel;
import com.yape.transaction.usecases.models.exception.TransactionNotFoundException;
import com.yape.transaction.usecases.out.TransactionDataAccessGateway;
import com.yape.transaction.entities.enums.TransactionStatus;
import com.yape.transaction.entities.models.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UpdateTransactionUseCase implements UpdateTransactionInputBoundary {

    private final TransactionDataAccessGateway transactionDataAccessGateway;

    public UpdateTransactionUseCase(TransactionDataAccessGateway transactionDataAccessGateway) {
        this.transactionDataAccessGateway = transactionDataAccessGateway;
    }

    @Override
    @Transactional
    public Transaction updateTransaction(UpdateTransactionModel updateTransactionModel) {
        log.info("UpdateTransactionUseCase updateTransaction updateTransactionModel {}", updateTransactionModel);
        Transaction transaction = transactionDataAccessGateway.findByTransactionExternalId(updateTransactionModel.convertEntity());
        if (transaction == null) {
            throw new TransactionNotFoundException("Unable to find transaction with given id");
        }
        transaction.setTransactionStatus(TransactionStatus.valueOf(updateTransactionModel.getStatus()));
        transaction.setUpdateAt(LocalDateTime.now());
        return transactionDataAccessGateway.update(transaction);
    }
}
