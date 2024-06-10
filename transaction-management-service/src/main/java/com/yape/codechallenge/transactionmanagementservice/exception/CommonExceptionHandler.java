package com.yape.codechallenge.transactionmanagementservice.exception;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CommonExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handleIllegalArgumentException(IllegalArgumentException e) {
        return GraphQLError.newError()
                .message(e.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleRuntimeException(RuntimeException e) {
        return GraphQLError.newError()
                .message(e.getMessage())
                .build();
    }

}
