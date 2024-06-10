package com.yape.codechallenge.antifraudvalidationservice.listener;

import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;
import com.yape.codechallenge.antifraudvalidationservice.service.AntiFraudValidationService;
import com.yape.codechallenge.antifraudvalidationservice.util.ConstantsUtils;
import com.yape.codechallenge.antifraudvalidationservice.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionEventListener {
    @Autowired
    private AntiFraudValidationService antiFraudValidationService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topicPattern = ConstantsUtils.CONSUMER_TOPIC, groupId = ConstantsUtils.GROUP_ID)
    public void consumeEvent(String eventMsg) {
        Map<String, Object> eventMap = ConvertUtils.jsonstring2Map(eventMsg);
        IncomingCreationEvent incomingCreationEvent = ConvertUtils.messageMapToTransactionEvent(eventMap);
        OutComingUpdatingEvent outComingUpdatingEvent = antiFraudValidationService.evaluateTransactionFraudRisk(incomingCreationEvent);

        String convertedJsonString = ConvertUtils.outcomingEventToJsonString(outComingUpdatingEvent);
        if(incomingCreationEvent.getOperationType().equals(ConstantsUtils.TRANSACTION_DB_OPERATION_CREATE)) {
            kafkaTemplate.send(ConstantsUtils.PRODUCER_TOPIC, convertedJsonString);
        }
    }

}
