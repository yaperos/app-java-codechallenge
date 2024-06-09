package com.yape.codechallenge.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yape.codechallenge.dto.TransactionResponseDto;
import com.yape.codechallenge.model.Transaction;
import com.yape.codechallenge.repository.TransactionRepository;
import com.yape.codechallenge.service.kafka.ProducerService;
import com.yape.codechallenge.service.util.UtilService;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProducerService producerService;
    private final UtilService utilService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ProducerService producerService,
            UtilService utilService) {
        this.transactionRepository = transactionRepository;
        this.producerService = producerService;
        this.utilService = utilService;
    }

    @Override
    public TransactionResponseDto createTransaction(Transaction transaction) {

        transaction.setTransactionStatus("pending");
        transaction.setCreatedAt(utilService.getOffsetDateTime());
        transaction.setTransactionType("1");
        var savedTransaction = transactionRepository.save(transaction);
        producerService.sendTransaction(savedTransaction);
        return TransactionResponseDto.builder().transactionExternalId(savedTransaction.getId().toString())
                .transactionType(TransactionResponseDto.Description.builder()
                        .name(savedTransaction.getTransactionType()).build())
                .transactionStatus(TransactionResponseDto.Description.builder()
                        .name(savedTransaction.getTransactionStatus()).build())
                .value(savedTransaction.getValue().toString()).createdAt(savedTransaction.getCreatedAt()).build();

    }

    @Override
    public Transaction getTransaction(UUID id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
