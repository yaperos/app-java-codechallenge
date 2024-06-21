package com.yape.transactional.service;

import com.yape.transactional.requestDTO.FinancialTransactionRequestDTO;
import org.springframework.http.ResponseEntity;

public interface TransactionalInterface {
    ResponseEntity<String> sendTransaction(FinancialTransactionRequestDTO request);
}
