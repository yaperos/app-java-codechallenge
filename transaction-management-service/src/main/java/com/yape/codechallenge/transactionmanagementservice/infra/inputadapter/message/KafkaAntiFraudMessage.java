package com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.message;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.TransactionsInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.ConstantsUtils;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class KafkaAntiFraudMessage {

    final
    TransactionsInputPort transactionsInputPort;

    public KafkaAntiFraudMessage(TransactionsInputPort transactionsInputPort) {
        this.transactionsInputPort = transactionsInputPort;
    }

    @KafkaListener(topics = ConstantsUtils.EventConstants.CONSUMER_AF_TOPIC, groupId = ConstantsUtils.EventConstants.GROUP_ID_3)
    public void listen(String message) {
        try {
            log.info("Received message: {}", message);
            Map<String, Object> eventMap = ConvertUtils.jsonstring2Map(message);
            Transactions updatedTransaction = transactionsInputPort.updateTransaction(eventMap.get("transactionExternalId").toString(), eventMap.get("evaluationResult").toString());
            log.info("Transaction updated: {}", updatedTransaction);
        } catch (Exception e) {
            log.error("Error updating transaction: {}", message, e);
        }
    }

}
