package com.yape.transaction.usecases;

import com.yape.transaction.entities.enums.TransferType;
import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.out.AntifraudKafkaGateway;
import com.yape.transaction.infrastructure.out.TransactionJpaGateway;
import com.yape.transaction.usecases.models.CreateTransactionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CreateTransactionUseCaseTests {

    @Mock
    private TransactionJpaGateway transactionJpaGateway;

    @Mock
    private AntifraudKafkaGateway antifraudKafkaGateway;

    private CreateTransactionUseCase createTransactionUseCase;

    @BeforeEach
    public void beforeAll() {
        createTransactionUseCase = new CreateTransactionUseCase(transactionJpaGateway, antifraudKafkaGateway);
    }

    @DisplayName("Create transaction Successfully")
    @Test
    public void createTransactionSuccessfully(){

        Mockito.when(transactionJpaGateway.create(Mockito.any())).thenReturn(Transaction
                .builder()
                .transactionExternalId(UUID.randomUUID())
                .build());

        Transaction transaction = createTransactionUseCase.createTransaction(CreateTransactionModel.builder()
                .value(1D)
                .accountExternalIdCredit(UUID.randomUUID())
                .accountExternalIdDebit(UUID.randomUUID())
                .transferTypeId(TransferType.NATIONAL.ordinal())
                .build());

        assertThat(transaction).isNotNull();
        assertThat(transaction.getTransactionExternalId()).isNotNull();
    }
}
