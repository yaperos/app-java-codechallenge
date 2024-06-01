package com.yape.transaction.validator.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createdTransactionTopic() {
        return TopicBuilder.name("createdTransactionTopic").build();
    }

    @Bean
    public NewTopic validatedTransactionTopic() {
        return TopicBuilder.name("validatedTransactionTopic").build();
    }
}
