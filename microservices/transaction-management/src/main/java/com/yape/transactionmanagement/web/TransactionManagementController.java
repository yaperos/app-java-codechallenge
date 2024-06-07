package com.yape.transactionmanagement.web;

import com.yape.transactionmanagement.model.request.RegisterTransactionRequest;
import com.yape.transactionmanagement.model.response.TransactionResponse;
import com.yape.transactionmanagement.service.TransactionManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("${application.core.api.path}/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionManagementController {

    private final TransactionManagementService transactionManagementService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<TransactionResponse> getAllTransactions() {
        return transactionManagementService.getAllTransactions()
            .doOnSubscribe(sub -> log.info("Iniciando busqueda todas las transacciones"))
            .doAfterTerminate(() -> log.info("Exito en la busqueda de todas las transacciones."));
    }

    @GetMapping(value = "/{transactionCode}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<TransactionResponse> getTransactionByCode(@PathVariable("transactionCode") String transactionCode) {
        return transactionManagementService.getTransactionByCode(transactionCode)
            .doOnSubscribe(sub -> log.info("Iniciando busqueda de transaccion por codigo {}", transactionCode))
            .doOnSuccess(response -> log.info("Exito en la busqueda de la transaccion."));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<Void> registerTransaction(@RequestBody RegisterTransactionRequest request) {
        return transactionManagementService.registerTransaction(request)
            .doOnSubscribe(sub -> log.info("Iniciando registro de nueva transaccion"))
            .doOnSuccess(response -> log.info("Exito en el registro de la transaccion."));
    }

}
