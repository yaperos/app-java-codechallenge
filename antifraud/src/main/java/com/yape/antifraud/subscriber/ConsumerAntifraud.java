package com.yape.antifraud.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yape.antifraud.enums.TransactionStatusEnum;
import com.yape.antifraud.requestDTO.FinancialTransactionRequestDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ConsumerAntifraud {

    private static ConsumerAntifraud consumerAntifraud;
    private KafkaConsumer<String, String> consumer;
    private static final String TOPIC = "antifraud";
    private ObjectMapper om = new ObjectMapper();

    private ConsumerAntifraud(){
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("src/main/resources/consumer.properties"));
            this.consumer = new KafkaConsumer<>(prop);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ConsumerAntifraud getInstance() {
        return (Objects.nonNull(consumerAntifraud)) ? consumerAntifraud : new ConsumerAntifraud();
    }

    public void start() {
        while(true) {
            try {
                consumer.subscribe(List.of(TOPIC));
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

                records.forEach(r -> {
                    validationAntifraud(r);
                });

            } catch (KafkaException e) {
                this.close();
            }

        }

    }

    private void validationAntifraud(ConsumerRecord<String, String> r) {
        FinancialTransactionRequestDTO request;
        try {
            request = this.om.readValue(r.value(), FinancialTransactionRequestDTO.class);
            request.setTranferTypeId(TransactionStatusEnum.APPROVED.getCode());
            if (request.getValue() > 1000.00) {
                request.setTranferTypeId(TransactionStatusEnum.REJECTED.getCode());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        this.consumer.close();;
    }

}
