package com.yape.transactionbff.application.usecase;

import com.yape.transactionbff.application.domain.Transaction;
import com.yape.transactionbff.application.port.in.RegisterTransactionCommand;
import com.yape.transactionbff.application.port.out.TransactionClient;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionModelRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterTransactionUseCase implements RegisterTransactionCommand {
    private final TransactionClient transactionClient;

    public RegisterTransactionUseCase(TransactionClient transactionClient) {
        this.transactionClient = transactionClient;
    }

    @Override
    public Transaction execute(Command command) {
        TransactionModelRequest request = TransactionModelRequest.builder()
                .value(command.getValue())
                .accountExternalIdDebit(command.getAccountExternalIdDebit())
                .accountExternalIdCredit(command.getAccountExternalIdCredit())
                .transferTypeId(command.getTransferTypeId())
                .build();

        return transactionClient.register(request).toDomain();
    }
}
