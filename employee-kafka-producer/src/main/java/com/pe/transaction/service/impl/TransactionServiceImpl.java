package com.pe.transaction.service.impl;


import com.pe.transaction.model.api.TransactionRequest;
import com.pe.transaction.model.api.TransactionResponse;
import com.pe.transaction.model.api.TransactionType;
import com.pe.transaction.model.entity.TransactionEntity;
import com.pe.transaction.producer.EventProducer;
import com.pe.transaction.repository.TransactionRepository;
import com.pe.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

   @Autowired
   TransactionRepository repository;

   @Autowired
   EventProducer eventProducer;

   @Override
   public TransactionResponse saveTransaction(TransactionRequest transactionRequest) {
      TransactionEntity entity = new TransactionEntity();
      TransactionResponse response = new TransactionResponse();

      // Validar la tipo de transferencia  [1=deposit , 2=retiro , 3=n/a]
      //Valida que la transaccion de producto cuenta debito tenga saldo != vacio
      if (transactionRequest.getAccountExternalIdDebit() != null) {
         entity.setTransactionExternalId(transactionRequest.getAccountExternalIdDebit());
         entity.setTransactionType("debit");
         entity.setTransactionStatus("Pendiente");
         entity.setValue(transactionRequest.getValue());
         entity.setCreatedAt(LocalDateTime.now().toString());
         log.info("Guardando en BD");
         TransactionEntity transactionEntity =  repository.save(entity);
         log.info("Enviando a Evento Producer-Transaccion Creada Al sistema Antifraude");
         eventProducer.sendDepositEvent(transactionEntity);
         response.setTransactionExternalId(entity.getTransactionExternalId());
         response.setTransactionType(new TransactionType(entity.getTransactionType()));
         response.setTransactionStatus(new TransactionType(entity.getTransactionStatus()));
         response.setValue(entity.getValue());
         response.setCreatedAt(entity.getCreatedAt());
      // Valida la transaccion de producto cuenta credito sea distinto a vacio
      }else if (transactionRequest.getAccountExternalIdCredit()!= null){
         entity.setTransactionExternalId(transactionRequest.getAccountExternalIdCredit());
         entity.setTransactionType("credit");
         entity.setTransactionStatus("Pendiente");
         entity.setValue(transactionRequest.getValue());
         entity.setCreatedAt(LocalDateTime.now().toString());
         log.info("Guardando en BD");
         TransactionEntity transactionEntity2 = repository.save(entity);
         log.info("Enviando a Evento Producer-Transaccion Creada Al sistema Antifraude");
         eventProducer.sendDepositEvent(transactionEntity2);
         response.setTransactionExternalId(entity.getTransactionExternalId());
         response.setTransactionType(new TransactionType(entity.getTransactionType()));
         response.setTransactionStatus(new TransactionType(entity.getTransactionStatus()));
         response.setValue(entity.getValue());
         response.setCreatedAt(entity.getCreatedAt());


      }else{ throw   new RuntimeException("Las  cuentas no deben estar vacias. ") ;}


return response;

   }

   @Override
   public TransactionResponse getTransaction(String transactionExternalId) {

      TransactionEntity transactionEntity =  repository.findByTransactionExternalId(transactionExternalId) ;
      TransactionResponse response = new TransactionResponse();
         response.setTransactionExternalId(transactionEntity.getTransactionExternalId());
         response.setTransactionType(new TransactionType(transactionEntity.getTransactionType()));
         response.setTransactionStatus(new TransactionType(transactionEntity.getTransactionStatus()));
         response.setValue(transactionEntity.getValue());
         response.setCreatedAt(transactionEntity.getCreatedAt());

      return response;
   }
}
