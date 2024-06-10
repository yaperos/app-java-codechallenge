package com.yape.codechallenge.transactionmanagementservice.infra.inputadapter.graphql;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.MessageBrokerInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.TransactionsInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.ValidatorInputPort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class TransactionsAPI {
    final
    TransactionsInputPort transactionsInputPort;
    final
    MessageBrokerInputPort messageBrokerInputPort;
    final
    ValidatorInputPort validatorInputPort;

    public TransactionsAPI(TransactionsInputPort transactionsInputPort, MessageBrokerInputPort messageBrokerInputPort, ValidatorInputPort validatorInputPort) {
        this.transactionsInputPort = transactionsInputPort;
        this.messageBrokerInputPort = messageBrokerInputPort;
        this.validatorInputPort = validatorInputPort;
    }

    @MutationMapping
    public Transactions createTransaction(@Argument String accountExternalIdDebit,@Argument String accountExternalIdCredit, @Argument int transferTypeId, @Argument BigDecimal value) {
        validatorInputPort.validateTransactionInputs(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value);
        return transactionsInputPort.createTransaction(accountExternalIdDebit, accountExternalIdCredit, transferTypeId, value);
    }

    @QueryMapping
    public Transactions getTransaction(@Argument String transactionExternalId) {
        return  messageBrokerInputPort.getById("transactions", transactionExternalId);
    }

}
