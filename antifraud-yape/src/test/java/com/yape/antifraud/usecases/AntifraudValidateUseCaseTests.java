package com.yape.antifraud.usecases;

import com.yape.antifraud.entities.enums.TransactionStatus;
import com.yape.antifraud.entities.models.Transaction;
import com.yape.antifraud.infrastructure.out.TransactionKafkaGateway;
import com.yape.antifraud.usecases.models.AntifraudValidateModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AntifraudValidateUseCaseTests {

    @Mock
    private TransactionKafkaGateway transactionKafkaGateway;

    private AntifraudValidateUseCase antifraudValidateUseCase;

    private Double valueLimit = 1000D;

    @BeforeEach
    public void beforeAll() {
        antifraudValidateUseCase = new AntifraudValidateUseCase(transactionKafkaGateway, valueLimit);
    }

    @DisplayName("Antifraud Validate transaction - Successfully (APPROVED)")
    @Test
    public void updateTransactionSuccessfully_approved(){

        Transaction transaction = antifraudValidateUseCase.antifraudValidate(AntifraudValidateModel.builder()
                .transactionExternalId(UUID.randomUUID())
                .value(999.99)
                .status(TransactionStatus.PENDING.name())
                .build());

        assertThat(transaction).isNotNull();
        assertEquals(transaction.getTransactionStatus().name(), TransactionStatus.APPROVED.name());
    }


    @DisplayName("Antifraud Validate transaction - Successfully (REJECTED)")
    @Test
    public void updateTransactionSuccessfully_rejected(){

        Transaction transaction = antifraudValidateUseCase.antifraudValidate(AntifraudValidateModel.builder()
                .transactionExternalId(UUID.randomUUID())
                .value(1000.1)
                .status(TransactionStatus.PENDING.name())
                .build());

        assertThat(transaction).isNotNull();
        assertEquals(transaction.getTransactionStatus().name(), TransactionStatus.REJECTED.name());
    }
}
