package com.yape.transactionbff.infra.in.adapter.graphql;

import com.yape.transactionbff.application.port.in.GetTransactionQuery;
import com.yape.transactionbff.application.port.in.RegisterTransactionCommand;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionCreateModelResponse;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionModelRequest;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionModelResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class TransactionControllerGraph {
    private final GetTransactionQuery getTransactionQuery;
    private final RegisterTransactionCommand registerTransactionCommand;

    public TransactionControllerGraph(GetTransactionQuery getTransactionQuery, RegisterTransactionCommand registerTransactionCommand) {
        this.getTransactionQuery = getTransactionQuery;
        this.registerTransactionCommand = registerTransactionCommand;
    }

    @QueryMapping
    public TransactionModelResponse getTransactionByCode(@Argument UUID code) {
        return TransactionModelResponse.fromDomain(this.getTransactionQuery.getTransactionByCode(code));
    }

    @QueryMapping
    public List<TransactionModelResponse> getAllTransactions() {
        return this.getTransactionQuery.getAllTransactions().stream()
                .map(TransactionModelResponse::fromDomain)
                .collect(Collectors.toList());
    }

    @MutationMapping
    public TransactionCreateModelResponse register(@Argument TransactionModelRequest request) {
        RegisterTransactionCommand.Command command = RegisterTransactionCommand.Command.builder()
                .accountExternalIdCredit(request.getAccountExternalIdCredit())
                .accountExternalIdDebit(request.getAccountExternalIdDebit())
                .transferTypeId(request.getTransferTypeId())
                .value(request.getValue())
                .build();

        return TransactionCreateModelResponse.fromDomain(this.registerTransactionCommand.execute(command));
    }

}