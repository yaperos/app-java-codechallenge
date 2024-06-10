package com.yape.codechallenge.transactionmanagementservice.application;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.inputport.MessageBrokerInputPort;
import com.yape.codechallenge.transactionmanagementservice.infra.outputport.QueryRepository;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.MapTransformUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageBrokerUseCase implements MessageBrokerInputPort {
    final
    QueryRepository queryRepository;

    Map<String,Class<?>> classes = Map.of(
            "transactions", Transactions.class
    );

    public MessageBrokerUseCase(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @Override
    public void updateReg(String table, Map<String, Object> reg) {
        queryRepository.save( reg, classes.get( table ) );

    }

    @Override
    public void insertReg(String table, Map<String, Object> reg) {
        queryRepository.save( reg, classes.get( table ) );

    }

    @Override
    public Transactions getById(String table, String id) {
        Map<String, Object> queryResult = queryRepository.getById(id, classes.get(table));
        return MapTransformUtils.map2Transactions(queryResult);
    }
}
