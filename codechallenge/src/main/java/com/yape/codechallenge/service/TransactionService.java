package com.yape.codechallenge.service;

import java.util.UUID;
import java.util.List;

import com.yape.codechallenge.dto.TransactionResponseDto;
import com.yape.codechallenge.model.Transaction;

public interface TransactionService {

    TransactionResponseDto createTransaction(Transaction transaction);

    Transaction getTransaction(UUID id);

    List<Transaction> getAllTransactions();
}
