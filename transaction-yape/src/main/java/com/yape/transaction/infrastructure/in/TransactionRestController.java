package com.yape.transaction.infrastructure.in;

import com.yape.transaction.usecases.in.CreateTransactionInputBoundary;
import com.yape.transaction.usecases.in.GetTransactionInputBoundary;
import com.yape.transaction.usecases.in.UpdateTransactionInputBoundary;
import com.yape.transaction.usecases.models.UpdateTransactionModel;
import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.models.CreateTransactionRequestModel;
import com.yape.transaction.infrastructure.models.GetTransactionRequestModel;
import com.yape.transaction.infrastructure.models.GetTransactionResponseModel;
import com.yape.transaction.infrastructure.models.UpdateTransactionRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class TransactionRestController {

    private final CreateTransactionInputBoundary createTransactionInputBoundary;
    private final GetTransactionInputBoundary getTransactionInputBoundary;
    private final UpdateTransactionInputBoundary updateTransactionInputBoundary;

    public TransactionRestController(CreateTransactionInputBoundary createTransactionInputBoundary,
                                     GetTransactionInputBoundary getTransactionInputBoundary,
                                     UpdateTransactionInputBoundary updateTransactionInputBoundary) {
        this.createTransactionInputBoundary = createTransactionInputBoundary;
        this.getTransactionInputBoundary = getTransactionInputBoundary;
        this.updateTransactionInputBoundary = updateTransactionInputBoundary;
    }

    @GetMapping("/transaction/{transactionExternalId}")
    public GetTransactionResponseModel getTransaction(@PathVariable String transactionExternalId) {
        log.info("TransactionRestController GetTransactionResponseModel transactionExternalId {}", transactionExternalId);
        GetTransactionRequestModel getTransactionRequestModel = GetTransactionRequestModel.builder()
                .transactionExternalId(transactionExternalId)
                .build();
        Transaction transaction =  getTransactionInputBoundary.getTransaction(getTransactionRequestModel.getUseCaseModel());
        return GetTransactionResponseModel.convertFrom(transaction);
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public GetTransactionResponseModel createTransaction(@RequestBody CreateTransactionRequestModel createTransactionRequestModel) {
        log.info("TransactionRestController createTransaction createTransactionRequestModel {}", createTransactionRequestModel);
        Transaction transaction =  createTransactionInputBoundary.createTransaction(createTransactionRequestModel.getUseCaseModel());
        return GetTransactionResponseModel.convertFrom(transaction);
    }

    @PatchMapping("/transaction/{transactionExternalId}")
    public GetTransactionResponseModel updateStatusTransaction(
            @RequestBody UpdateTransactionRequestModel updateTransactionRequestModel,
            @PathVariable("transactionExternalId") String transactionExternalId) {
        log.info("TransactionRestController updateStatusTransaction updateTransactionRequestModel {} transactionExternalId {}",
                updateTransactionRequestModel, transactionExternalId);
        UpdateTransactionModel updateTransactionModel = updateTransactionRequestModel.getUseCaseModel();
        updateTransactionModel.setTransactionExternalId(UUID.fromString(transactionExternalId));
        Transaction transaction =  updateTransactionInputBoundary.updateTransaction(updateTransactionModel);
        return GetTransactionResponseModel.convertFrom(transaction);
    }
}
