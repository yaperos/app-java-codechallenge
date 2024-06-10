package com.yape.codechallenge.transactionmanagementservice.application;

import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionType;
import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.TransactionsInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.outputport.CommandRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionsUseCase implements TransactionsInputPort {
    final
    CommandRepository entityRepository;
    private final JdbcTemplate jdbcTemplate;

    public TransactionsUseCase(CommandRepository entityRepository, JdbcTemplate jdbcTemplate) {
        this.entityRepository = entityRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transactions createTransaction(String externalIdDebit, String externalIdCredit, int transferTypeId, BigDecimal value) {
        Transactions transaction = Transactions.builder()
                .transactionExternalId(UUID.randomUUID().toString())
                .accountExternalIdDebit(externalIdDebit)
                .accountExternalIdCredit(externalIdCredit)
                .transferTypeId(transferTypeId)
                .transactionStatus(TransactionStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .transactionType(TransactionType.TRANSFER)
                .value(value)
                .build();
        return entityRepository.save(transaction);
    }

    @Override
    public Transactions updateTransaction(String externalTransactionId, String status) {
        Transactions transaction = entityRepository.getById(externalTransactionId, Transactions.class);
        if (transaction != null) {
            transaction.setTransactionStatus(TransactionStatus.valueOf(status));
            String sql = "UPDATE Transactions SET transactionStatus = ? WHERE transactionExternalId = ?";
            jdbcTemplate.update(sql, status, externalTransactionId);
        }
        return transaction;
    }

    @Override
    public Transactions getTransactionById(String customerId) {
        return null;
    }
}
