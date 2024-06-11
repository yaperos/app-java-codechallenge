package com.yape.codechallenge.antifraudvalidationservice;

import com.yape.codechallenge.antifraudvalidationservice.config.KafkaConfig;
import com.yape.codechallenge.antifraudvalidationservice.util.ConstantsUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class KafkaProducerConfigTest {

    @InjectMocks
    private KafkaConfig.KafkaProducerConfig kafkaProducerConfig;

    @Mock
    private Map<String, Object> configProps;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProducerFactoryWithCorrectConfiguration() {
        when(configProps.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)).thenReturn(ConstantsUtils.EventConstants.KAFKA_BOOSTRAP_SERVERS);
        when(configProps.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG)).thenReturn(StringSerializer.class);
        when(configProps.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG)).thenReturn(StringSerializer.class);

        ProducerFactory<String, String> producerFactory = kafkaProducerConfig.producerFactory();

        assertInstanceOf(DefaultKafkaProducerFactory.class, producerFactory);
    }

    @Test
    void shouldReturnKafkaTemplateWithCorrectProducerFactory() {
        KafkaTemplate<String, String> kafkaTemplate = kafkaProducerConfig.kafkaTemplate();
        ProducerFactory<String, String> producerFactory = kafkaTemplate.getProducerFactory();

        assertEquals(producerFactory, kafkaTemplate.getProducerFactory());
    }
}