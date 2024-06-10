package com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.message;

import com.yape.codechallenge.transactionmanagementservice.infra.inputport.MessageBrokerInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.ConvertUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaDBMessage {

    final
    MessageBrokerInputPort messageBrokerInputPort;

    public KafkaDBMessage(MessageBrokerInputPort messageBrokerInputPort) {
        this.messageBrokerInputPort = messageBrokerInputPort;
    }

    @KafkaListener(topicPattern = "cqrs-.public.*", groupId = "group1")
    public void messageHandler(@Payload( required = false) String eventMsg) {
        if ( eventMsg == null ) return;

        Map<String, Object> event = ConvertUtils.jsonstring2Map( eventMsg );

        Map<String, Object> payload = (Map<String, Object>) event.get("payload");
        String operation = (String) payload.get("op");
        String table = (String) ((Map<String, Object>) payload.get("source")).get("table");

        if ( operation.equals("u") ) {
            messageBrokerInputPort.updateReg(table, (Map<String, Object>) payload.get("after"));
        } else if ( operation.equals("c") ) {
            messageBrokerInputPort.insertReg(table, (Map<String, Object>) payload.get("after"));
        }

    }



}
