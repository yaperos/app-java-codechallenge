package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.application.ValidatorUseCase;
import com.yape.codechallenge.transactionmanagementservice.exception.CommonExceptionHandler;
import graphql.GraphQLError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorUseCaseTest {
    private ValidatorUseCase validatorUseCase;

    @BeforeEach
    public void setup() {
        validatorUseCase = new ValidatorUseCase();
    }

    @Test
    void validateTransactionInputsShouldNotThrowExceptionWhenInputsAreValid() {
        assertDoesNotThrow(() -> validatorUseCase.validateTransactionInputs("PE2212345678901234567890", "PE2209876543210987654321", 1, BigDecimal.valueOf(100)));
    }

    @Test
    void validateTransactionInputsShouldThrowExceptionWhenIbanIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> validatorUseCase.validateTransactionInputs("InvalidIban", "PE09876543210987654321", 1, BigDecimal.valueOf(100)));
    }

    @Test
    void validateTransactionInputsShouldThrowExceptionWhenIbansAreDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> validatorUseCase.validateTransactionInputs("PE12345678901234567890", "PE12345678901234567890", 1, BigDecimal.valueOf(100)));
    }

    @Test
    void validateTransactionInputsShouldThrowExceptionWhenTransferTypeIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> validatorUseCase.validateTransactionInputs("PE12345678901234567890", "PE09876543210987654321", 0, BigDecimal.valueOf(100)));
    }

    @Test
    void validateTransactionInputsShouldThrowExceptionWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> validatorUseCase.validateTransactionInputs("PE12345678901234567890", "PE09876543210987654321", 1, BigDecimal.ZERO));
    }
}
