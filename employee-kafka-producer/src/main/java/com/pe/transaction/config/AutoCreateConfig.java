package com.pe.transaction.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

 
@Lazy
@Configuration
public class AutoCreateConfig {
    @Bean
	public NewTopic depositEvent() {

		Map<String,String> configurations = new HashMap<>();
		return TopicBuilder.name("transactionEventTopic")
				.partitions(3)
				.replicas(1)
				.build();
	}
	
}
