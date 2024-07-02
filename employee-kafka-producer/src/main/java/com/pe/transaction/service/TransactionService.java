package com.pe.transaction.service;


import com.pe.transaction.model.api.TransactionRequest;
import com.pe.transaction.model.api.TransactionResponse;
 

public interface TransactionService {

   TransactionResponse saveTransaction(TransactionRequest transactionRequest);
   TransactionResponse getTransaction(String transactionExternalId);
}
