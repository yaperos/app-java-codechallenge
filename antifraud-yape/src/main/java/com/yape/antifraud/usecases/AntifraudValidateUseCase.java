package com.yape.antifraud.usecases;

import com.yape.antifraud.usecases.in.AntifraudValidateInputBoundary;
import com.yape.antifraud.usecases.models.AntifraudValidateModel;
import com.yape.antifraud.usecases.out.TransactionGateway;
import com.yape.antifraud.entities.enums.TransactionStatus;
import com.yape.antifraud.entities.models.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AntifraudValidateUseCase implements AntifraudValidateInputBoundary {

    private final TransactionGateway transactionGateway;

    private final Double limitAmount;

    public AntifraudValidateUseCase(TransactionGateway transactionGateway,
                                    @Value("${antifraud.limit-amount}") Double limitAmount) {
        this.transactionGateway = transactionGateway;
        this.limitAmount = limitAmount;
    }

    @Override
    public Transaction antifraudValidate(AntifraudValidateModel antifraudValidateModel) {
        log.info("AntifraudValidateUseCase antifraudValidate antifraudValidateModel {}", antifraudValidateModel);
        Transaction transaction = antifraudValidateModel.convertEntity();

        if (transaction.getValue() >= limitAmount) {
            transaction.setTransactionStatus(TransactionStatus.REJECTED);
        } else {
            transaction.setTransactionStatus(TransactionStatus.APPROVED);
        }
        transactionGateway.updateStatus(transaction);
        log.info("AntifraudValidateUseCase antifraudValidate transaction {}", transaction);
        return transaction;
    }
}
