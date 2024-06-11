package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.application.MessageBrokerUseCase;
import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.outputport.QueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MessageBrokerUseCaseTest {

    @Mock
    private QueryRepository queryRepository;

    private MessageBrokerUseCase messageBrokerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messageBrokerUseCase = new MessageBrokerUseCase(queryRepository);
    }

    @Test
    void shouldUpdateRegSuccessfully() {
        String table = "transactions";
        Map<String, Object> reg = new HashMap<>();
        reg.put("transactionExternalId", "TX1234567890");
        reg.put("transactionStatus", "PENDING");

        messageBrokerUseCase.updateReg(table, reg);

        verify(queryRepository).save(any(), any());
    }

    @Test
    void shouldInsertRegSuccessfully() {
        String table = "transactions";
        Map<String, Object> reg = new HashMap<>();
        reg.put("transactionExternalId", "TX1234567890");
        reg.put("transactionStatus", "PENDING");

        messageBrokerUseCase.insertReg(table, reg);

        verify(queryRepository).save(any(), any());
    }

    @Test
    void shouldGetByIdSuccessfully() {
        String table = "transactions";
        String id = "TX1234567890";
        Map<String, Object> mockTransactionMap = new HashMap<>();
        mockTransactionMap.put("transactionexternalid", "TX1234567890");
        mockTransactionMap.put("transactionstatus", "PENDING");
        mockTransactionMap.put("transactiontype", "TRANSFER");
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("value", "100");
        valueMap.put("scale", 2);
        mockTransactionMap.put("value", valueMap);
        mockTransactionMap.put("createdat", System.currentTimeMillis());

        when(queryRepository.getById(anyString(), any())).thenReturn(mockTransactionMap);

        Transactions result = messageBrokerUseCase.getById(table, id);

        assertEquals("TX1234567890", result.getTransactionExternalId());
        verify(queryRepository).getById(anyString(), any());
    }
}