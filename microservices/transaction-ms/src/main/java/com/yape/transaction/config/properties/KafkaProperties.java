package com.yape.transaction.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaProperties {
    private String bootstrapServers;
    private TopicProperties topic;
    private ConsumerKafkaProperties consumer;
}
