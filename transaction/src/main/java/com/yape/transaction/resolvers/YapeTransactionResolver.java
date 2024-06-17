package com.yape.transaction.resolvers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.yape.transaction.dtos.YapeTransactionInsertion;
import com.yape.transaction.dtos.YapeTransactionSingleRetrieval;
import com.yape.transaction.services.YapeTransactionService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class YapeTransactionResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

	@Autowired
	private final YapeTransactionService transactionService;

    public YapeTransactionResolver(YapeTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @MutationMapping
    public YapeTransactionSingleRetrieval createTransaction(@Argument YapeTransactionInsertion input) {
        return transactionService.createTransaction(input);
    }

    @QueryMapping
    public YapeTransactionSingleRetrieval getTransaction(@Argument UUID transactionExternalId) {
        return transactionService.getTransaction(transactionExternalId);
    }
}