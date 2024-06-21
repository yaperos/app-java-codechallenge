package com.yape.transactional.facade;

import com.yape.transactional.requestDTO.FinancialTransactionRequestDTO;
import com.yape.transactional.service.TransactionalInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionalFacade {

    @Autowired
    private TransactionalInterface transactionalInterface;

    public ResponseEntity<String> sendTransaction(FinancialTransactionRequestDTO request) {
        return this.transactionalInterface.sendTransaction(request);
    }
}
