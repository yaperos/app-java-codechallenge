package com.yape.transactional.publisher;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerAntifraud {

    private static ProducerAntifraud producerAntifraud;
    private KafkaProducer<String, String> kafkaProducer;

    private static final String TOPIC = "antifraud";
    private static final Integer PARTITION = 0;

    private ProducerAntifraud() {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("src/main/resources/producer.properties"));
            this.kafkaProducer = new KafkaProducer<>(prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProducerAntifraud getInstance() {
        return (Objects.nonNull(producerAntifraud)) ? producerAntifraud : new ProducerAntifraud();
    }

    public void publishMessage(String key, String value) {
        try {
            var record = new ProducerRecord<String, String>(TOPIC, PARTITION, key, value);
            Future<RecordMetadata> send = this.kafkaProducer.send(record);
        } catch (KafkaException ex) {
            this.close();
        }
    }

    public void close() {
        this.kafkaProducer.close();
    }
}
