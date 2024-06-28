package com.yaperos.service;

import com.yaperos.dto.Transaction;
import com.yaperos.dto.TransactionDto;
import com.yaperos.dto.TransactionResponseDTO;
import com.yaperos.dto.TransactionStatus;
import com.yaperos.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public TransactionResponseDTO createTransaction(TransactionDto transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAccountExternalIdDebit(transactionDTO.getAccountExternalIdDebit());
        transaction.setAccountExternalIdCredit(transactionDTO.getAccountExternalIdCredit());
        transaction.setTransferTypeId(transactionDTO.getTransferTypeId());
        transaction.setValue(transactionDTO.getValue());
        transaction.setCreatedAt(new Date());
        transaction.setStatus(TransactionStatus.PENDING);

        if (transactionDTO.getValue() > 1000) {
            transaction.setStatus(TransactionStatus.REJECTED);
        } else {
            transaction.setStatus(TransactionStatus.APPROVED);
        }

        transaction = transactionRepository.save(transaction);
        kafkaTemplate.send("transactions", transaction);

        return mapToResponseDTO(transaction);
    }

    public TransactionResponseDTO getTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        return mapToResponseDTO(transaction);
    }

    private TransactionResponseDTO mapToResponseDTO(Transaction transaction) {
        TransactionResponseDTO response = new TransactionResponseDTO();
        response.setTransactionExternalId(transaction.getTransactionExternalId());
        response.setValue(transaction.getValue());
        response.setCreatedAt(transaction.getCreatedAt());

        TransactionResponseDTO.TransactionType type = new TransactionResponseDTO.TransactionType();
        type.setName("Type " + transaction.getTransferTypeId());
        response.setTransactionType(type);

        TransactionResponseDTO.TransactionStatus status = new TransactionResponseDTO.TransactionStatus();
        status.setName(transaction.getStatus().toString());
        response.setTransactionStatus(status);

        return response;
    }

}
