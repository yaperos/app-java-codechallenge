package com.yape.transaction.validator.infrastructure.config;

import com.yape.transaction.validator.domain.event.ValidatedTransactionEventPort;
import com.yape.transaction.validator.domain.service.DomainValidatorService;
import com.yape.transaction.validator.domain.service.ValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    ValidatorService DomainValidatorService(ValidatedTransactionEventPort port) {
        return new DomainValidatorService(port);
    }
}