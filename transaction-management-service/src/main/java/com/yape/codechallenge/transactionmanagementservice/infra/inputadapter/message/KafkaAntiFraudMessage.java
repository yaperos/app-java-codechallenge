package com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.message;

import com.yape.codechallenge.transactionmanagementservice.infra.inputport.TransactionsInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaAntiFraudMessage {

    @Autowired
    TransactionsInputPort transactionsInputPort;

    @KafkaListener(topics = "transaction-status", groupId = "group3")
    public void listen(String message) {
        try {
            System.out.println("Received message in group foo: " + message);
            Map<String, Object> eventMap = ConvertUtils.jsonstring2Map(message);
            transactionsInputPort.updateTransaction(eventMap.get("transactionExternalId").toString(), eventMap.get("evaluationResult").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
