package com.yape.transaction.usecases;

import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.out.TransactionJpaGateway;
import com.yape.transaction.usecases.models.GetTransactionModel;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class GetTransactionUseCaseTests {

    @Mock
    private TransactionJpaGateway transactionDataAccessGateway;

    private GetTransactionUseCase getTransactionUseCase;

    @BeforeEach
    public void beforeAll() {
        getTransactionUseCase = new GetTransactionUseCase(transactionDataAccessGateway);
    }

    @DisplayName("Get transaction - Successfully")
    @Test
    public void getTransactionSuccessfully(){

        Mockito.when(transactionDataAccessGateway.findByTransactionExternalId(Mockito.any())).thenReturn(Transaction.builder().build());

        Transaction transaction = getTransactionUseCase.getTransaction(GetTransactionModel.builder()
                .transactionExternalId(UUID.randomUUID())
                .build());

        assertThat(transaction).isNotNull();
    }


    @DisplayName("Get transaction - Not found")
    @Test
    public void getTransactionSuccessError_notFound(){

        Mockito.when(transactionDataAccessGateway.findByTransactionExternalId(Mockito.any())).thenReturn(null);

        assertThrows(TransactionNotFoundException.class, () -> {
            Transaction transaction = getTransactionUseCase.getTransaction(GetTransactionModel.builder()
                .transactionExternalId(UUID.randomUUID())
                .build());
        });
    }
}
