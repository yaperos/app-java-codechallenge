package com.yape.transaction.usecases;

import com.yape.transaction.usecases.models.exception.TransactionNotFoundException;
import com.yape.transaction.usecases.in.GetTransactionInputBoundary;
import com.yape.transaction.usecases.models.GetTransactionModel;
import com.yape.transaction.usecases.out.TransactionDataAccessGateway;
import com.yape.transaction.entities.models.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetTransactionUseCase implements GetTransactionInputBoundary {

    private final TransactionDataAccessGateway transactionDataAccessGateway;

    public GetTransactionUseCase(TransactionDataAccessGateway transactionDataAccessGateway) {
        this.transactionDataAccessGateway = transactionDataAccessGateway;
    }

    @Override
    public Transaction getTransaction(GetTransactionModel getTransactionModel) {
        log.info("GetTransactionUseCase getTransaction getTransactionModel {}", getTransactionModel);
        Transaction transaction = transactionDataAccessGateway.findByTransactionExternalId(getTransactionModel.convertEntity());
        if (transaction == null) {
            throw new TransactionNotFoundException("Unable to find transaction with given id");
        }
        return transaction;
    }
}
