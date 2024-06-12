package com.yape.transaction.application.usecase;

import com.yape.transaction.application.port.in.UpdateTransactionCommand;
import com.yape.transaction.application.port.out.CacheRepository;
import com.yape.transaction.application.port.out.TransactionRepository;
import com.yape.transaction.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UpdateTransactionUseCase implements UpdateTransactionCommand {
    private final TransactionRepository repository;
    private final CacheRepository cacheRepository;

    public UpdateTransactionUseCase(TransactionRepository repository, CacheRepository cacheRepository) {
        this.repository = repository;
        this.cacheRepository = cacheRepository;
    }

    @Transactional
    @Override
    public void execute(Command command) {
        Transaction transaction = this.cacheRepository.findTransactionById(command.getId());
        this.repository.updateStatus(transaction.getId(), command.getStatus().name(), LocalDateTime.now());
        log.info("updated id:{}", transaction.getId());

        this.cacheRepository.removeTransactionById(transaction.getId());
    }

}