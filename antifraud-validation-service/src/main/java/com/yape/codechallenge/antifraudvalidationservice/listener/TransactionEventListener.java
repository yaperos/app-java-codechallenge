package com.yape.codechallenge.antifraudvalidationservice.listener;

import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;
import com.yape.codechallenge.antifraudvalidationservice.service.AntiFraudValidationService;
import com.yape.codechallenge.antifraudvalidationservice.util.ConstantsUtils;
import com.yape.codechallenge.antifraudvalidationservice.util.ConvertUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionEventListener {
    private final AntiFraudValidationService antiFraudValidationService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionEventListener(AntiFraudValidationService antiFraudValidationService, KafkaTemplate<String, String> kafkaTemplate) {
        this.antiFraudValidationService = antiFraudValidationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topicPattern = ConstantsUtils.EventConstants.CONSUMER_TOPIC, groupId = ConstantsUtils.EventConstants.GROUP_ID)
    public void consumeEvent(String eventMsg) {
        Map<String, Object> eventMap = ConvertUtils.convertJsonStringToMap(eventMsg);
        IncomingCreationEvent incomingCreationEvent = ConvertUtils.messageMapToTransactionEvent(eventMap);
        OutComingUpdatingEvent outComingUpdatingEvent = antiFraudValidationService.evaluateTransactionFraudRisk(incomingCreationEvent);

        String convertedJsonString = ConvertUtils.outcomingEventToJsonString(outComingUpdatingEvent);
        if(incomingCreationEvent.getOperationType().equals(ConstantsUtils.TRANSACTION_DB_OPERATION_CREATE)) {
            kafkaTemplate.send(ConstantsUtils.EventConstants.PRODUCER_TOPIC, convertedJsonString);
        }
    }

}
