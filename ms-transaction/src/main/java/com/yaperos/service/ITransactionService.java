package com.yaperos.service;

import com.yaperos.dto.TransactionDto;
import com.yaperos.dto.TransactionResponseDTO;

public interface ITransactionService {
    TransactionResponseDTO createTransaction(TransactionDto transactionDTO);
}
