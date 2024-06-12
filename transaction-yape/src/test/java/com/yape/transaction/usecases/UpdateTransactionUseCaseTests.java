package com.yape.transaction.usecases;

import com.yape.transaction.entities.enums.TransactionStatus;
import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.out.TransactionJpaGateway;
import com.yape.transaction.usecases.models.UpdateTransactionModel;
import com.yape.transaction.usecases.models.exception.TransactionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UpdateTransactionUseCaseTests {

    @Mock
    private TransactionJpaGateway transactionDataAccessGateway;

    private UpdateTransactionUseCase updateTransactionUseCase;

    @BeforeEach
    public void beforeAll() {
        updateTransactionUseCase = new UpdateTransactionUseCase(transactionDataAccessGateway);
    }

    @DisplayName("Update transaction - Successfully (APPROVED)")
    @Test
    public void updateTransactionSuccessfully(){

        Mockito.when(transactionDataAccessGateway.findByTransactionExternalId(Mockito.any())).thenReturn(
                Transaction.builder()
                        .transactionStatus(TransactionStatus.APPROVED)
                        .build()
        );

        Mockito.when(transactionDataAccessGateway.update(Mockito.any())).thenReturn(
                Transaction.builder()
                        .transactionStatus(TransactionStatus.APPROVED)
                        .build()
        );

        Transaction transaction = updateTransactionUseCase.updateTransaction(UpdateTransactionModel.builder()
                .transactionExternalId(UUID.randomUUID())
                .status(TransactionStatus.APPROVED.name())
                .build());

        assertThat(transaction).isNotNull();
        assertEquals(transaction.getTransactionStatus().name(), TransactionStatus.APPROVED.name());
    }


    @DisplayName("Update transaction - Not found")
    @Test
    public void getTransactionSuccessError_notFound(){

        Mockito.when(transactionDataAccessGateway.findByTransactionExternalId(Mockito.any())).thenReturn(null);

        assertThrows(TransactionNotFoundException.class, () -> {
            updateTransactionUseCase.updateTransaction(UpdateTransactionModel.builder()
                .transactionExternalId(UUID.randomUUID())
                .status(TransactionStatus.APPROVED.name())
                .build());
        });
    }
}
