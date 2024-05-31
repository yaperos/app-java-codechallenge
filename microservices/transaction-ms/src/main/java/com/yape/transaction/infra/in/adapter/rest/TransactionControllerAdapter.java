package com.yape.transaction.infra.in.adapter.rest;

import com.yape.transaction.application.port.in.GetTransactionQuery;
import com.yape.transaction.application.port.in.RegisterTransactionCommand;
import com.yape.transaction.infra.in.adapter.rest.model.TransactionModelRequest;
import com.yape.transaction.infra.in.adapter.rest.model.TransactionModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction-ms")
public class TransactionControllerAdapter {

    private final GetTransactionQuery getTransactionQuery;
    private final RegisterTransactionCommand registerTransactionCommand;

    public TransactionControllerAdapter(GetTransactionQuery getTransactionQuery,
                                        RegisterTransactionCommand registerTransactionCommand){
        this.getTransactionQuery = getTransactionQuery;
        this.registerTransactionCommand = registerTransactionCommand;
    }
    @GetMapping("/{code}")
    public ResponseEntity<TransactionModelResponse> getTransactionByCode(@PathVariable UUID code) {
        TransactionModelResponse response = TransactionModelResponse.fromDomain(this.getTransactionQuery.getTransactionByCode(code));
        return  ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionModelResponse>> getAllTransactions() {
        List<TransactionModelResponse> response = this.getTransactionQuery.getAllTransactions().stream()
                .map(TransactionModelResponse::fromDomain)
                .collect(Collectors.toList());
        return  ResponseEntity.ok(response);
    }

   @PostMapping
    public ResponseEntity<TransactionModelResponse> register(@RequestBody TransactionModelRequest request) {
        RegisterTransactionCommand.Command command = RegisterTransactionCommand.Command.builder()
                .accountExternalIdCredit(request.getAccountExternalIdCredit())
                .accountExternalIdDebit(request.getAccountExternalIdDebit())
                .transferTypeId(request.getTransferTypeId())
                .value(request.getValue())
                .build();
        TransactionModelResponse response = TransactionModelResponse.fromDomain(this.registerTransactionCommand.execute(command));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
