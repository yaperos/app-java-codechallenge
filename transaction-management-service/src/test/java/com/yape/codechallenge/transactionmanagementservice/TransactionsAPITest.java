package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.graphql.TransactionsAPI;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.MessageBrokerInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.TransactionsInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.ValidatorInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionsAPITest {

    @Mock
    private TransactionsInputPort transactionsInputPort;

    @Mock
    private MessageBrokerInputPort messageBrokerInputPort;

    @Mock
    private ValidatorInputPort validatorInputPort;

    private TransactionsAPI transactionsAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionsAPI = new TransactionsAPI(transactionsInputPort, messageBrokerInputPort, validatorInputPort);
    }

    @Test
    void shouldCreateTransactionSuccessfully() {
        String accountExternalIdDebit = "PE2212310230111231023012";
        String accountExternalIdCredit = "PE2212310230111231023011";
        int transferTypeId = 1;
        BigDecimal value = BigDecimal.valueOf(100);

        Transactions mockTransaction = Transactions.builder().build();
        when(transactionsInputPort.createTransaction(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value)).thenReturn(mockTransaction);

        Transactions result = transactionsAPI.createTransaction(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value);

        assertNotNull(result);
        verify(transactionsInputPort).createTransaction(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value);
        verify(validatorInputPort).validateTransactionInputs(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value);
    }

    @Test
    void shouldGetTransactionSuccessfully() {
        String transactionExternalId = "TX1234567890";
        Transactions mockTransaction = Transactions.builder().build();
        when(messageBrokerInputPort.getById(anyString(), anyString())).thenReturn(mockTransaction);

        Transactions result = transactionsAPI.getTransaction(transactionExternalId);

        assertNotNull(result);
        verify(messageBrokerInputPort).getById(anyString(), eq(transactionExternalId));
    }
}