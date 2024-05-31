package com.yape.transaction.config;

import com.yape.transaction.config.properties.SpringConfigurationProperties;
import com.yape.transaction.infra.in.adapter.kafka.model.TransactionInModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConfig {

  private final SpringConfigurationProperties springConfigurationProperties;
  private static final String EARLIEST = "earliest";
  public KafkaConfig(SpringConfigurationProperties springConfigurationProperties) {
    this.springConfigurationProperties = springConfigurationProperties;
  }

  @Bean
  public ConsumerFactory<String, TransactionInModel> transactionConsumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, springConfigurationProperties.getKafka().getBootstrapServers());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class);

    props.put(ConsumerConfig.GROUP_ID_CONFIG, springConfigurationProperties.getKafka().getConsumer().getGroupId());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, EARLIEST);
    return new DefaultKafkaConsumerFactory<>(props, new ErrorHandlingDeserializer<>(), new ErrorHandlingDeserializer<>(new JsonDeserializer<>(TransactionInModel.class)));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, TransactionInModel> transactionKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, TransactionInModel>
            factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(transactionConsumerConfigs());
    return factory;
  }

}
