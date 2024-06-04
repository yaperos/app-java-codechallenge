package com.yape.transaction.builder;

import com.yape.transaction.model.entity.Transaction;
import com.yape.transaction.model.request.TransactionRequest;
import com.yape.transaction.model.response.TransactionResponse;
import com.yape.transaction.model.response.ValueName;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.yape.transaction.util.DateUtil.parseDateToString;

public class TransactionBuilder {

    public static TransactionResponse buildTransactionToResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionExternalId(transaction.getTransactionExternalId())
                .transactionStatus(ValueName.builder().name(transaction.getStatus()).build())
                .transactionType(ValueName.builder().name(transaction.getTransferTypeId()).build())
                .value(transaction.getValue())
                .createdAt(parseDateToString(transaction.getCreatedAt()))
                .build();
    }

    public static Transaction buildTransactionFromRequest(TransactionRequest transactionRequest){
        return Transaction.builder()
                .transactionExternalId(UUID.randomUUID().toString())
                .transferTypeId(transactionRequest.getTransferTypeId())
                .accountExternalIdCredit(transactionRequest.getAccountExternalIdCredit())
                .accountExternalIdDebit(transactionRequest.getAccountExternalIdDebit())
                .status("PENDING")
                .value(transactionRequest.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
