package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.message.KafkaAntiFraudMessage;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.TransactionsInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class KafkaAntiFraudMessageTest {

    @Mock
    private TransactionsInputPort transactionsInputPort;

    @InjectMocks
    private KafkaAntiFraudMessage kafkaAntiFraudMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listenShouldUpdateTransactionWhenMessageIsValid() {
        String validMessage = "{\"transactionExternalId\":\"123\",\"evaluationResult\":\"PASS\"}";

        kafkaAntiFraudMessage.listen(validMessage);

        verify(transactionsInputPort, times(1)).updateTransaction(anyString(), anyString());
    }

    @Test
    void listenShouldNotUpdateTransactionWhenMessageIsInvalid() {
        String invalidMessage = "Invalid message";

        kafkaAntiFraudMessage.listen(invalidMessage);

        verify(transactionsInputPort, never()).updateTransaction(anyString(), anyString());
    }

    @Test
    void listenShouldNotUpdateTransactionWhenMessageIsNull() {
        kafkaAntiFraudMessage.listen(null);

        verify(transactionsInputPort, never()).updateTransaction(anyString(), anyString());
    }

}
