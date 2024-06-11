package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.application.TransactionsUseCase;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.outputport.CommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionUseCaseTest {
    @Mock
    private CommandRepository commandRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private TransactionsUseCase transactionsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionsUseCase = new TransactionsUseCase(commandRepository, jdbcTemplate);
    }

    @Test
    void shouldCreateTransactionSuccessfully() {
        String accountExternalIdDebit = "PE2212310230111231023012";
        String accountExternalIdCredit = "PE2212310230111231023011";
        int transferTypeId = 1;
        BigDecimal value = BigDecimal.valueOf(100);

        Transactions mockTransaction = Transactions.builder().build();
        when(commandRepository.save(any(Transactions.class))).thenReturn(mockTransaction);

        Transactions result = transactionsUseCase.createTransaction(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value);

        assertEquals(mockTransaction, result);
        verify(commandRepository).save(any(Transactions.class));
    }

    @Test
    void shouldUpdateTransactionSuccessfully() {
        String externalTransactionId = "TX1234567890";
        String status = "ACCEPTED";

        Transactions mockTransaction = Transactions.builder().transactionStatus(TransactionStatus.PENDING).build();
        when(commandRepository.getById(anyString(), any())).thenReturn(mockTransaction);

        Transactions result = transactionsUseCase.updateTransaction(externalTransactionId, status);

        assertEquals(TransactionStatus.valueOf(status), result.getTransactionStatus());
        verify(jdbcTemplate).update(anyString(), anyString(), anyString());
    }
}
