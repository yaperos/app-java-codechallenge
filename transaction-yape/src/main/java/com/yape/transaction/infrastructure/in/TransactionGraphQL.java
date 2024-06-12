package com.yape.transaction.infrastructure.in;

import com.yape.transaction.usecases.in.CreateTransactionInputBoundary;
import com.yape.transaction.usecases.in.GetTransactionInputBoundary;
import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.models.CreateTransactionRequestModel;
import com.yape.transaction.infrastructure.models.GetTransactionRequestModel;
import com.yape.transaction.infrastructure.models.GetTransactionResponseModel;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class TransactionGraphQL {

    private final CreateTransactionInputBoundary createTransactionInputBoundary;
    private final GetTransactionInputBoundary getTransactionInputBoundary;

    public TransactionGraphQL(CreateTransactionInputBoundary createTransactionInputBoundary,
                              GetTransactionInputBoundary getTransactionInputBoundary) {
        this.createTransactionInputBoundary = createTransactionInputBoundary;
        this.getTransactionInputBoundary = getTransactionInputBoundary;
    }

    @QueryMapping
    public GetTransactionResponseModel getTransaction(@Argument @Valid GetTransactionRequestModel getTransactionRequestModel) {
        log.info("TransactionGraphQL getTransaction getTransactionRequestModel {}", getTransactionRequestModel);
        Transaction transaction = getTransactionInputBoundary.getTransaction(getTransactionRequestModel.getUseCaseModel());
        return GetTransactionResponseModel.convertFrom(transaction);

    }

    @MutationMapping
    public GetTransactionResponseModel createTransaction(@Argument @Valid CreateTransactionRequestModel createTransactionRequestModel) {
        log.info("TransactionGraphQL createTransaction getTransactionRequestModel {}", createTransactionRequestModel);
        Transaction transaction = createTransactionInputBoundary.createTransaction(createTransactionRequestModel.getUseCaseModel());
        return GetTransactionResponseModel.convertFrom(transaction);
    }
}