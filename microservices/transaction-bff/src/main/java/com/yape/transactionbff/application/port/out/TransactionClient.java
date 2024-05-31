package com.yape.transactionbff.application.port.out;


import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionModelRequest;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionModelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "transactions",
        url = "http://localhost:8080/transaction-ms"

)
public interface TransactionClient {
    @GetMapping(value = "/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    TransactionModelResponse getTransactionById(@PathVariable("code") UUID code);

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TransactionModelResponse> getAllTransactions();

    @PostMapping(consumes  = MediaType.APPLICATION_JSON_VALUE)
    TransactionModelResponse register(@RequestBody TransactionModelRequest request);
}
