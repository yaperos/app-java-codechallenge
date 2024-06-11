package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.exception.CommonExceptionHandler;
import graphql.GraphQLError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonExceptionHandlerTest {
    @InjectMocks
    private CommonExceptionHandler commonExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleIllegalArgumentExceptionShouldReturnGraphQLErrorWithCorrectMessage() {
        String exceptionMessage = "Illegal argument exception message";
        IllegalArgumentException exception = new IllegalArgumentException(exceptionMessage);

        GraphQLError error = commonExceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(exceptionMessage, error.getMessage());
    }

    @Test
    void handleRuntimeExceptionShouldReturnGraphQLErrorWithCorrectMessage() {
        String exceptionMessage = "Runtime exception message";
        RuntimeException exception = new RuntimeException(exceptionMessage);

        GraphQLError error = commonExceptionHandler.handleRuntimeException(exception);

        assertEquals(exceptionMessage, error.getMessage());
    }
}
