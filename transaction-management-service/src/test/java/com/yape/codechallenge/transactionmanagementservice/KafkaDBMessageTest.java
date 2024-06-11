package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.message.KafkaDBMessage;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.MessageBrokerInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class KafkaDBMessageTest {

    @Mock
    private MessageBrokerInputPort messageBrokerInputPort;

    @InjectMocks
    private KafkaDBMessage kafkaDBMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void messageHandlerShouldUpdateRegWhenOperationIsU() {
        String eventMsg = "{\"payload\":{\"op\":\"u\",\"after\":{\"key\":\"value\"},\"source\":{\"table\":\"table\"}}}";

        kafkaDBMessage.messageHandler(eventMsg);

        verify(messageBrokerInputPort, times(1)).updateReg(anyString(), anyMap());
    }

    @Test
    void messageHandlerShouldInsertRegWhenOperationIsC() {
        String eventMsg = "{\"payload\":{\"op\":\"c\",\"after\":{\"key\":\"value\"},\"source\":{\"table\":\"table\"}}}";

        kafkaDBMessage.messageHandler(eventMsg);

        verify(messageBrokerInputPort, times(1)).insertReg(anyString(), anyMap());
    }

    @Test
    void messageHandlerShouldDoNothingWhenOperationIsNotUorC() {
        String eventMsg = "{\"payload\":{\"op\":\"d\",\"after\":{\"key\":\"value\"},\"source\":{\"table\":\"table\"}}}";

        kafkaDBMessage.messageHandler(eventMsg);

        verify(messageBrokerInputPort, never()).updateReg(anyString(), anyMap());
        verify(messageBrokerInputPort, never()).insertReg(anyString(), anyMap());
    }

    @Test
    void messageHandlerShouldDoNothingWhenEventMsgIsNull() {
        kafkaDBMessage.messageHandler(null);

        verify(messageBrokerInputPort, never()).updateReg(anyString(), anyMap());
        verify(messageBrokerInputPort, never()).insertReg(anyString(), anyMap());
    }
}