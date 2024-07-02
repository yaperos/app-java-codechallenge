package com.pe.transaction.producer;

import java.util.List;
 
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.pe.demo.transactionproducer.util.GsonConverter;
import com.pe.transaction.model.entity.TransactionEntity;

 

@Slf4j
@Component
@AllArgsConstructor
public class EventProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendDepositEvent(TransactionEntity transactionEntity) {
        log.info("recibiendo datos..");
        String key = transactionEntity.getTransactionExternalId();
        String value = GsonConverter.convertToJson(transactionEntity);
        log.info("convertir a json el parametro value:" + value);
        ProducerRecord<String, String> producerRecord = buildProducerRecord(key, value);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(
                result -> {
                    assert result != null;
                    log.debug("Message Sent Successfully for the key:{} and the value is {}, partition is {}",
                            key, value, result.getRecordMetadata().partition());
                },
                ex -> log.error("onFailure", ex)
		);
    }

    private ProducerRecord<String, String> buildProducerRecord(String key, String value) {
        List<Header> recordHeaders = List.of(new RecordHeader("deposit-event-source", value.getBytes()));
        return new ProducerRecord<>("transactionEventTopic", null, key, value, recordHeaders);
    }

}
