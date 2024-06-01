package com.yape.transaction.manager.infrastructure.config;

import com.yape.transaction.manager.domain.event.CreateTransactionEventPort;
import com.yape.transaction.manager.domain.repository.TransactionRepository;
import com.yape.transaction.manager.domain.service.DomainTransactionService;
import com.yape.transaction.manager.domain.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    TransactionService DomainTransactionService(TransactionRepository repository, CreateTransactionEventPort port) {
        return new DomainTransactionService(repository, port);
    }
}