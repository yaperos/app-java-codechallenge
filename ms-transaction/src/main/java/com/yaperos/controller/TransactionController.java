package com.yaperos.controller;

import com.yaperos.dto.TransactionDto;
import com.yaperos.dto.TransactionResponseDTO;
import com.yaperos.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping
    public TransactionResponseDTO createTransaction(@RequestBody TransactionDto transactionDTO) {
        return transactionService.createTransaction(transactionDTO);
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransaction(@PathVariable UUID id) {
        return transactionService.getTransaction(id);
    }
}
