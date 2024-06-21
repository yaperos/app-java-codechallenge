package com.yape.transactional.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yape.transactional.entity.TransactionEntity;
import com.yape.transactional.enums.TransactionStatusEnum;
import com.yape.transactional.repository.TransactionRepository;
import com.yape.transactional.requestDTO.FinancialTransactionRequestDTO;
import com.yape.transactional.publisher.ProducerAntifraud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionalServiceImpl implements TransactionalInterface{

    @Autowired
    private ObjectMapper om;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<String> sendTransaction(FinancialTransactionRequestDTO request) {
        try {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setAccountExternalIdCredit(request.getAccountExternalIdCredit());
            transactionEntity.setAccountExternalIdDebit(request.getAccountExternalIdDebit());
            transactionEntity.setTransactionStatus(TransactionStatusEnum.PENDING.getCode());
            transactionEntity.setValue(request.getValue());

            this.transactionRepository.save(transactionEntity);
            request.setId(transactionEntity.getId());
            this.sendMessageKafka(request);
            return new ResponseEntity<>("Transacción satisfactoria", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Sucedio un error al prosar la transacción", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private void sendMessageKafka(FinancialTransactionRequestDTO request) throws JsonProcessingException {
        var kafkaProducer = ProducerAntifraud.getInstance();
        kafkaProducer.publishMessage(String.valueOf(Math.random()), om.writeValueAsString(request));
    }
}
