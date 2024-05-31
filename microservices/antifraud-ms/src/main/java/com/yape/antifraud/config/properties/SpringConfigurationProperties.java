package com.yape.antifraud.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpringConfigurationProperties {
    private KafkaProperties kafka;
}
