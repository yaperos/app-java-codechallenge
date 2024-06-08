package com.yape.transactiongateway.config.client.impl;

import com.yape.transactiongateway.config.client.TransactionRestClient;
import com.yape.transactiongateway.model.dto.RegisterTransactionRequest;
import com.yape.transactiongateway.model.dto.TransactionResponse;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TransactionRestClientImpl implements TransactionRestClient {

    private WebClient restClient;

    @Value("${http-client.transaction-management.base-url}")
    private String baseUrl;

    @Value("${http-client.transaction-management.read-timeout}")
    private long readTimeout;

    @Value("${http-client.transaction-management.write-timeout}")
    private long writeTimeout;

    @PostConstruct
    public void init() {
        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, ((Long) writeTimeout).intValue())
            .responseTimeout(Duration.ofMillis(readTimeout))
            .doOnConnected(conn ->
                conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

        this.restClient = WebClient.builder()
            .baseUrl(baseUrl)
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }

    @Override
    public Flux<TransactionResponse> getAllTransactions() {
        log.info("Begin HTTP Call [GET] transactions");
        return restClient.get()
            .uri("/transactions")
            .retrieve()
            .bodyToFlux(TransactionResponse.class)
            .doAfterTerminate(() -> log.info("OK <-- Respuesta de Éxito en la petición HTTP"))
            .doOnError(err -> log.error("Error <-- Ocurrió un error en la petición HTTP. Msg {}", err.toString()))
            ;
    }

    @Override
    public Mono<TransactionResponse> getTransactionByCode(String transactionCode) {
        log.info("Begin HTTP Call [GET] transactions/{txCode}");
        return restClient.get()
            .uri("/transactions/{transactionCode}", transactionCode)
            .retrieve()
            .bodyToMono(TransactionResponse.class)
            .doOnSuccess(success -> log.info("OK <-- Respuesta de Éxito en la petición HTTP"))
            .doOnError(err -> log.error("Error <-- Ocurrió un error en la petición HTTP. Msg {}", err.toString()))
            ;
    }

    @Override
    public Mono<Void> registerTransaction(RegisterTransactionRequest request) {
        log.info("Begin HTTP Call [POST] transactions with request {}", request.toString());
        return restClient.post()
            .uri("/transactions")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(Void.class)
            .doOnSuccess(success -> log.info("OK <-- Respuesta de Éxito en la petición HTTP"))
            .doOnError(err -> log.error("Error <-- Ocurrió un error en la petición HTTP. Msg {}", err.toString()))
            ;
    }

}
